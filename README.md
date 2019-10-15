# SJPL Intermediate Java Curriculum Materials

This repository contains core Java projects for the SJPL Intermediate Java Curriculum as well as utilities for extracting labs for classes.

To create a lab, start by making any changes you want to in the working tree. For example, you might change a class to omit an implementation for a method you want the students to fill in and add a comment giving them a hint.

When you have modified the code to your liking, extract a patch like this:

`git diff > patches/<labName>.diff`

Commit the patch and discard the rest of your changes.

To extract a pristine lab, just run:

`./extract <labName>`

To try it out, run: `./extract lab1` and follow the instructions to load the extracted lab onto the flash drives.
