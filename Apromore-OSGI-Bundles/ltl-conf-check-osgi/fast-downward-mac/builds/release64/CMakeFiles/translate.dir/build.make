# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.8

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


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
CMAKE_COMMAND = /usr/local/Cellar/cmake/3.8.2/bin/cmake

# The command to remove a file.
RM = /usr/local/Cellar/cmake/3.8.2/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/src"

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/builds/release64"

# Utility rule file for translate.

# Include the progress variables for this target.
include CMakeFiles/translate.dir/progress.make

translate: CMakeFiles/translate.dir/build.make
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --blue --bold "Copying translator module into output directory"
	/usr/local/Cellar/cmake/3.8.2/bin/cmake -E copy_directory /Users/Giuseppe/Desktop/Fast-Downard\ OK\ MAC/src/translate /Users/Giuseppe/Desktop/Fast-Downard\ OK\ MAC/builds/release64/bin/./translate
.PHONY : translate

# Rule to build all files generated by this target.
CMakeFiles/translate.dir/build: translate

.PHONY : CMakeFiles/translate.dir/build

CMakeFiles/translate.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/translate.dir/cmake_clean.cmake
.PHONY : CMakeFiles/translate.dir/clean

CMakeFiles/translate.dir/depend:
	cd "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/builds/release64" && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/src" "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/src" "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/builds/release64" "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/builds/release64" "/Users/Giuseppe/Desktop/Fast-Downard OK MAC/builds/release64/CMakeFiles/translate.dir/DependInfo.cmake" --color=$(COLOR)
.PHONY : CMakeFiles/translate.dir/depend

