#!/bin/bash

javacc_opts="-output_directory=parser "

if [ "$1" == "jtb" ]; then
  echo "==> Running javacc with JTB output ..."
  cd parser
  java -jar ../../bin/jtb132.jar ../fn.jj -o jtb.out.jj
  cd -

  echo "==> Running javacc with JTB output ..."
  javacc $javacc_opts parser/jtb.out.jj

else
  javacc $javacc_opts fn-semantic.jj
fi

javac -classpath .:parser Parse.java
