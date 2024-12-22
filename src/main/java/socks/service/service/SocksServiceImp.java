package socks.service.service;

import socks.service.entity.SocksEntity;
import socks.service.exception.SocksNotFoundException;
import socks.service.model.Socks;
import socks.service.repositories.SocksRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SocksServiceImp implements SocksService {

    @Autowired
    private SocksRepository socksRepository;

    @Autowired
    private MappingService<SocksEntity, Socks> mappingService;

    @Override
    @Transactional
    public Integer income(String color, Integer cotton, Long amount) {
        Integer rgb = Color.decode(color).getRGB();
        if (socksRepository.existsSocksEntityByColorAndCotton(rgb, cotton))
            return socksRepository.incomeSocks(rgb, cotton, amount);
        return socksRepository.save(new SocksEntity(rgb, cotton, amount < 0 ? 0L : amount)).getId() > 0 ? 1 : 0;
    }

    @Override
    @Transactional
    public Integer outcome(String color, Integer cotton, Long amount) {
        return socksRepository.outcomeSocks(Color.decode(color).getRGB(), cotton, amount);
    }

    @Override
    public ArrayList<Socks> getAllSocksByFilter(Integer color, Integer moreThan, Integer lessThan, Integer equal, Integer cottonMoreThen, Integer cottonLessThen) {
        return StreamSupport.stream(socksRepository.findAll(color, moreThan, lessThan, equal, cottonMoreThen, cottonLessThen).spliterator(), false)
                .map(mappingService::toModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Socks changeSocks(Long id, Socks changedSocks) {
        SocksEntity entity = socksRepository.findById(id).orElseThrow(() -> new SocksNotFoundException("No socks were found with this Id"));
        entity.setColor(Color.decode(changedSocks.getColor()).getRGB());
        entity.setCotton(changedSocks.getCotton());
        entity.setAmount(changedSocks.getAmount());
        return mappingService.toModel(socksRepository.save(entity));
    }

    @Override
    @Transactional
    public ArrayList<Socks> upload(MultipartFile file) {
        ArrayList<SocksEntity> data = new ArrayList<>();
        try (InputStream in = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(in);
            Sheet sheet = workbook.getSheetAt(0);
            StreamSupport.stream(sheet.spliterator(), false).skip(1).forEach(row ->
                    data.add(new SocksEntity(
                            Color.decode(row.getCell(0).getRichStringCellValue().getString()).getRGB(),
                            (int) row.getCell(1).getNumericCellValue(),
                            (long) row.getCell(2).getNumericCellValue())));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return StreamSupport.stream(socksRepository.saveAll(duplicateFilteringAndSave(data)).spliterator(), false)
                .map(mappingService::toModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Modifies existing socks
     *
     * @param data list of socks
     * @return returns a list of socks that have no records.
     */
    @Transactional
    private ArrayList<SocksEntity> duplicateFilteringAndSave(ArrayList<SocksEntity> data) {
        return data.stream()
                .filter(socks -> {
                            if (socksRepository
                                    .existsSocksEntityByColorAndCotton(
                                            socks.getColor(), socks.getCotton())) {
                                socksRepository.incomeSocks(socks.getColor(), socks.getCotton(), socks.getAmount());
                                return false;
                            } else {
                                return true;
                            }
                        }
                ).collect(Collectors.toCollection(ArrayList::new));
    }
}
