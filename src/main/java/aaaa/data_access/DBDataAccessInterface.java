package aaaa.data_access;

import java.util.List;

import aaaa.entity.User;

/**
 * Interface for data access operations. Provides methods for saving and reading data
 * for a specific user. Implementations of this interface should define the persistence
 * logic for saving and retrieving user-specific data.
 *
 * @param <T> the type of data being accessed
 */
public interface DBDataAccessInterface<T> {

    /**
     * Save an object to the specified file.
     *
     * @param userID  the user using the service
     * @param dataPoint the object to save
     * @return a userObject
     */
    User saveData(int userID, T dataPoint);

    /**
     * Read a list of objects from the specified file.
     *
     * @param userID  the user using the service
     * @return a list of objects read from the file
     */
    List<T> readData(int userID);
}