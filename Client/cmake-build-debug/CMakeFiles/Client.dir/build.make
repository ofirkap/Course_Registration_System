# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /snap/clion/138/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /snap/clion/138/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/spl211/CLionProjects/Client

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/spl211/CLionProjects/Client/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Client.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Client.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Client.dir/flags.make

CMakeFiles/Client.dir/src/BGRSClient.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/BGRSClient.cpp.o: ../src/BGRSClient.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Client.dir/src/BGRSClient.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/BGRSClient.cpp.o -c /home/spl211/CLionProjects/Client/src/BGRSClient.cpp

CMakeFiles/Client.dir/src/BGRSClient.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/BGRSClient.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/Client/src/BGRSClient.cpp > CMakeFiles/Client.dir/src/BGRSClient.cpp.i

CMakeFiles/Client.dir/src/BGRSClient.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/BGRSClient.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/Client/src/BGRSClient.cpp -o CMakeFiles/Client.dir/src/BGRSClient.cpp.s

CMakeFiles/Client.dir/src/ConnectionHandler.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/ConnectionHandler.cpp.o: ../src/ConnectionHandler.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/Client.dir/src/ConnectionHandler.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/ConnectionHandler.cpp.o -c /home/spl211/CLionProjects/Client/src/ConnectionHandler.cpp

CMakeFiles/Client.dir/src/ConnectionHandler.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/ConnectionHandler.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/Client/src/ConnectionHandler.cpp > CMakeFiles/Client.dir/src/ConnectionHandler.cpp.i

CMakeFiles/Client.dir/src/ConnectionHandler.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/ConnectionHandler.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/Client/src/ConnectionHandler.cpp -o CMakeFiles/Client.dir/src/ConnectionHandler.cpp.s

CMakeFiles/Client.dir/src/Reader.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/Reader.cpp.o: ../src/Reader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/Client.dir/src/Reader.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/Reader.cpp.o -c /home/spl211/CLionProjects/Client/src/Reader.cpp

CMakeFiles/Client.dir/src/Reader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/Reader.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/Client/src/Reader.cpp > CMakeFiles/Client.dir/src/Reader.cpp.i

CMakeFiles/Client.dir/src/Reader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/Reader.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/Client/src/Reader.cpp -o CMakeFiles/Client.dir/src/Reader.cpp.s

CMakeFiles/Client.dir/src/Writer.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/Writer.cpp.o: ../src/Writer.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/spl211/CLionProjects/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/Client.dir/src/Writer.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/Writer.cpp.o -c /home/spl211/CLionProjects/Client/src/Writer.cpp

CMakeFiles/Client.dir/src/Writer.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/Writer.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/spl211/CLionProjects/Client/src/Writer.cpp > CMakeFiles/Client.dir/src/Writer.cpp.i

CMakeFiles/Client.dir/src/Writer.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/Writer.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/spl211/CLionProjects/Client/src/Writer.cpp -o CMakeFiles/Client.dir/src/Writer.cpp.s

# Object files for target Client
Client_OBJECTS = \
"CMakeFiles/Client.dir/src/BGRSClient.cpp.o" \
"CMakeFiles/Client.dir/src/ConnectionHandler.cpp.o" \
"CMakeFiles/Client.dir/src/Reader.cpp.o" \
"CMakeFiles/Client.dir/src/Writer.cpp.o"

# External object files for target Client
Client_EXTERNAL_OBJECTS =

Client: CMakeFiles/Client.dir/src/BGRSClient.cpp.o
Client: CMakeFiles/Client.dir/src/ConnectionHandler.cpp.o
Client: CMakeFiles/Client.dir/src/Reader.cpp.o
Client: CMakeFiles/Client.dir/src/Writer.cpp.o
Client: CMakeFiles/Client.dir/build.make
Client: CMakeFiles/Client.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/spl211/CLionProjects/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking CXX executable Client"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Client.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Client.dir/build: Client

.PHONY : CMakeFiles/Client.dir/build

CMakeFiles/Client.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Client.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Client.dir/clean

CMakeFiles/Client.dir/depend:
	cd /home/spl211/CLionProjects/Client/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/spl211/CLionProjects/Client /home/spl211/CLionProjects/Client /home/spl211/CLionProjects/Client/cmake-build-debug /home/spl211/CLionProjects/Client/cmake-build-debug /home/spl211/CLionProjects/Client/cmake-build-debug/CMakeFiles/Client.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Client.dir/depend

