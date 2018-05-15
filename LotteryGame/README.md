This folder contains the two files for the lottery game application:
The SweetMillion.java is the implementation of the lottery game and the Randomizer.java serves to generate the random numbers
needed for the game
The QuickPicker.java is an updated implmenentation of the lottery game and allows for configuration by reading property files from different games
The QuickPickerException.java is a custom exception class that is thrown either if the property file for the chosen game was not found or if the file is missing one of its attributes such as name, pool size, etc.
