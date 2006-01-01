package org.apache.axis2.schema;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * Copyright 2004,2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This is a bean class that captures all the compiler options.
 * Right now the compiler options consist of the following
 * 1. output file location - A folder with necessary rights for the
 * schema compiler to write the files
 */
public class CompilerOptions {

    /**
     * Generated output file
     */
    private File outputLocation;
    private String packageName = null;

    /**
     * This flag tells the databinder to either write the output or
     * not. if this is set to true it will write the output at once.
     * if not the outputter will populate the 
     */
    private boolean writeOutput = false;

    /**
     * This flag determines whether the generated classes are wrapped or not
     * if the wrapper flag is true, then only a single file will be generated
     */
    private boolean wrapClasses = false;

    public boolean isWriteOutput() {
        return writeOutput;
    }

    public void setWriteOutput(boolean writeOutput) {
        this.writeOutput = writeOutput;
    }

    public boolean isWrapClasses() {
        return wrapClasses;
    }

    public void setWrapClasses(boolean wrapClasses) {
        this.wrapClasses = wrapClasses;
    }

    public String getPackageName() {
        return packageName;
    }

    public CompilerOptions setPackageName(String packageName) {
        // Validate the package name.
        if (packageName != null && testValue(packageName)) {
            this.packageName = packageName;
        } else {
            throw new RuntimeException("Unsupported value!");
        }
        return this;
    }

    public File getOutputLocation() {
        return outputLocation;
    }

    public CompilerOptions setOutputLocation(File outputLocation) {
        this.outputLocation = outputLocation;
        return this;
    }

    private boolean testValue(String wordToMatch) {
        Pattern pat = Pattern.compile("^(\\w+\\.)+$");
        Matcher m = pat.matcher(wordToMatch);
        return m.matches();
    }
}
