package socks.service.service;

import socks.service.model.Socks;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface SocksService {

    /**
     * This function reduces the number of socks in stock by a specified amount.
     * If the number of socks remaining in the warehouse is less than the amount being reduced,
     * the amount of socks in the stocks becomes 0.
     *
     * @param color  the color of socks in the form of hex
     * @param cotton the percentage of cotton in the sock
     * @param amount the number of socks to change the quantity in stock
     * @return the number of records that have been updated or created.
     */
    Integer income(String color, Integer cotton, Long amount);

    /**
     * This function increases the amount of socks in stock by a specified value.
     * If the value is negative, it reduces the number of socks.
     * If there are no socks of the specified color and percentage of cotton content in stock, a new record will be created
     *
     * @param color  the color of socks in the form of hex
     * @param cotton the percentage of cotton in the sock
     * @param amount the number of socks to change the quantity in stock
     * @return the number of records that have been updated or created.
     */
    Integer outcome(String color, Integer cotton, Long amount);

    /**
     * Get all the socks that are in stock
     *
     * @param color          the color of socks that is required
     * @param moreThan       the amount of socks that is more than the value
     * @param lessThan       the amount of socks that is less than the value
     * @param equal          the amount of socks is equal to
     * @param cottonMoreThen the percentage of cotton content in the sock is more than the value
     * @param cottonLessThen the percentage of cotton content in the sock is less than the value
     * @return a list of all socks in stock that meet the filters
     */
    ArrayList<Socks> getAllSocksByFilter(Integer color, Integer moreThan, Integer lessThan, Integer equal, Integer cottonMoreThen, Integer cottonLessThen);

    /**
     * Change the parameters of the socks
     *
     * @param id           The identifier of the socks that need to be changed
     * @param changedSocks socks with changed parameters
     * @return returns the changed socks
     */
    Socks changeSocks(Long id, Socks changedSocks);

    /**
     * Upload data from a file. If the socks already exist, then the record about them is not duplicated,
     * and their amount is not replaced, but increased by the value from the file.
     *
     * @param file the file containing the uploaded data
     * @return returns only the created files
     */
    ArrayList<Socks> upload(MultipartFile file);
}
