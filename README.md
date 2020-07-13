# ModelFileSystem
Created for CMPT276.
A model file system created in JAVA with software engineering design patterns.
## Functions
The program can currently create directories or files in said directories.
An external file can also be added to directories created in the model file system.
Search function allows the user to search for both files and directories given a string.
Write function allows the user to change directory/file names.

## Quirk
The FileSystem will handle the situation of a child file/directory that attempts to be
created before the parent is created as an invalid input. The FileSystem will notify the
user with such error, dictating either the file or directory parent does not exist nothing
will be created.
