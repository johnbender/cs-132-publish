#!/bin/bash

javacc_opts="-output_directory=parser "

cd parser
java -jar ../bin/jtb132.jar ../fn.jj -o jtb.out.jj
cd -

echo "==> Running javacc with JTB output ..."
javacc $javacc_opts parser/jtb.out.jj

javac -classpath .:parser Parse.java
