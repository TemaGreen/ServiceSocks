package socks.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import socks.service.model.Socks;
import socks.service.request.RequestChangeSocks;
import socks.service.service.SocksService;

import java.awt.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/socks")
public class SocksController {

    @Autowired
    private SocksService socksService;

    @Operation(
            summary = "Increases the amount of socks in stock"
    )
    @PostMapping("/income")
    public Integer income(@RequestBody RequestChangeSocks request) {
        return socksService.income(request.getColor(), request.getCotton(), request.getAmount());
    }

    @Operation(
            summary = "Decreases the amount of socks in stock"
    )
    @PostMapping("/outcome")
    public Integer outcome(@RequestBody RequestChangeSocks request) {
        return socksService.outcome(request.getColor(), request.getCotton(), request.getAmount());
    }

    @Operation(
            summary = "Getting a list of socks in stock",
            description = "Returns a list of all socks, containing the Id, the color of the sock in hexadecimal format," +
                    " the percentage of cotton in the material, and the current stock amount")
    @GetMapping
    public ArrayList<Socks> getSocks(
            @Parameter(description = "Filter by the color of socks")
            @RequestParam(name = "color", required = false) String color,

            @Parameter(description = "Filter socks which the amount is more than a  value")
            @RequestParam(name = "moreThan", required = false) Integer moreThan,

            @Parameter(description = "Filter socks which the amount is less than a  value")
            @RequestParam(name = "lessThan", required = false) Integer lessThan,

            @Parameter(description = "Filter socks, the amount of which equals a value")
            @RequestParam(name = "equal", required = false) Integer equal,

            @Parameter(description = "Filter socks which the percentage of cotton is more than a  value")
            @RequestParam(name = "cottonMoreThen", required = false) Integer cottonMoreThen,

            @Parameter(description = "Filter socks which the percentage of cotton is less than a  value")
            @RequestParam(name = "cottonLessThen", required = false) Integer cottonLessThen) {
        if (color != null)
            return socksService.getAllSocksByFilter(Color.decode(color).getRGB(), moreThan, lessThan, equal, cottonMoreThen, cottonLessThen);
        return socksService.getAllSocksByFilter(null, moreThan, lessThan, equal, cottonMoreThen, cottonLessThen);
    }

    @Operation(
            summary = "Change the parameters of socks",
            description = "Allows you to change the parameters of socks (color, percentage of cotton, quantity)")
    @PutMapping("/{id}")
    public Socks changeSocks(
            @PathVariable Long id,
            @RequestBody RequestChangeSocks request) {
        return socksService.changeSocks(id, new Socks(request.getColor(), request.getCotton(), request.getAmount()));
    }

    @Operation(
            summary = "Uploading a data file",
            description = "Extracts data from an Excel file. The file must contain data the color socks in hexadecimal " +
                    "format, the percentage of cotton content in the product, and the quantity in stock. " +
                    "The correctness of the data is not verified, so the user is responsible for this.  " +
                    "The first line with headers skip. If the socks with the same parameters already exists, then " +
                    "increase its quantity by the specified value. If not, we add a new entry for the socks.")
    @PostMapping("/batch")
    public ArrayList<Socks> uploadSocks(@RequestBody MultipartFile file) {
        return socksService.upload(file);
    }
}
