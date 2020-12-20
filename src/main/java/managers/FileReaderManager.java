package managers;

import dataProvider.ConfigReader;

//FileReaderManager class is used for managing different readers
//It stores and provides one instance of each
public class FileReaderManager {
    private static final FileReaderManager fileReaderManager = new FileReaderManager();
    private ConfigReader configReader;

    private FileReaderManager() {

    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ConfigReader getConfigReader() {
        if (configReader == null) configReader = new ConfigReader();
        return configReader;
    }
}
