package socks.service.request;

import socks.service.exception.ColorFormatException;
import socks.service.exception.UnacceptablePercentageCottonException;
import io.swagger.v3.oas.annotations.media.Schema;

public class RequestChangeSocks {

    @Schema(description = "The color of the sock to be changed is represented as a string in hexadecimal format, which is then converted into RGB", example = "#FFFFFF")
    private String color;

    @Schema(description = "The percentage of cotton in socks to be changed to", example = "50")
    private Integer cotton;

    @Schema(description = "The amount of socks to be changed to ", example = "25")
    private Long amount;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (!checkColor(color))
            throw new ColorFormatException("The entered color does not conform to format. Example: #FFFFFF or 0xFFFFFF");
        this.color = color;
    }

    private boolean checkColor(String color) {
        if (color.startsWith("#")) {
            return color.substring(1).matches("[a-fA-F\\d]{6}");
        } else if (color.startsWith("0x") || color.startsWith("0X")) {
            return color.substring(2).matches("[a-fA-F\\d]{6}");
        }
        return true;
    }

    public Integer getCotton() {
        return cotton;
    }

    public void setCotton(Integer cotton) throws UnacceptablePercentageCottonException {
        if (cotton < 0 || cotton > 100)
            throw new UnacceptablePercentageCottonException("Exceeding the permitted limits from 0 to 100 of cotton content");
        this.cotton = cotton;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public RequestChangeSocks(String color, Integer cotton, Long amount) {
       if (!checkColor(color))
            throw new ColorFormatException("The entered color does not conform to format. Example: #FFFFFF or 0xFFFFFF");
        if (cotton < 0 || cotton > 100)
            throw new UnacceptablePercentageCottonException("Exceeding the permitted limits from 0 to 100 of cotton content");
        this.color = color;
        this.amount = amount;
        this.cotton = cotton;

    }

    public RequestChangeSocks() {
    }
}
