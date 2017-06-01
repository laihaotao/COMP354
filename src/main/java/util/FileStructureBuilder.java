package util;

import java.io.File;
import java.io.FileFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ERIC_LAI on 2017-04-08.
 */
public class FileStructureBuilder {

    public static void main(String[] args) {
        StringBuilder strBuilder = new StringBuilder();

        String separator = File.separator;
        String codePath = "." + separator + "src" + separator + "main" + separator + "java";
        final int base = getDepth(codePath);

        int fileCounter = 0;
        LinkedList<File> fileStack = new LinkedList<>();
        File[] codeFiles = getSubFiles(codePath);

        // perform dfs, collect all source code files
        if (codeFiles != null && codeFiles.length != 0) {
            // add all files or directories into the stack
            Collections.addAll(fileStack, codeFiles);

            while (!fileStack.isEmpty()) {
                File currentFile = fileStack.pop();
                buildString(strBuilder, currentFile, base);

                File[] subFiles = getSubFiles(currentFile.getPath());
                List<File> tmp = new LinkedList<>();

                // check all item under current path, update the file counter
                // and add directories in to the stack
                for (File f : subFiles) {
                    if (f.isFile()) {
                        fileCounter++;
                        buildString(strBuilder, f, base);
                    }
                    if (f.isDirectory()) {
                        tmp.add(f);
                    }
                }
                for (int i = tmp.size() - 1; i >= 0; i--) {
                    fileStack.addFirst(tmp.get(i));
                }
            }

            System.out.println("total source files number: " + fileCounter);
            System.out.println(strBuilder.toString());
        }

        // if the code path is empty, throw an exception
        else {
            throw new RuntimeException("The current directory: " + codePath + " is empty");
        }

    }

    private static void buildString(StringBuilder strBuilder, File currentFile, int base) {
        strBuilder.append(indent(getDepth(currentFile.getPath()) - base))
                .append(currentFile.getName())
                .append("\n");
    }

    private static File[] getSubFiles(String path) {
        return new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // filter all file whose name begin with '.' (system or configuration files)
                return pathname.getName().charAt(0) != '.';
            }
        });
    }

    private static int getDepth(String path) {
        int depth = 0;
        char[] arr = path.toCharArray();
        for (char ch : arr) {
            if (ch == File.separatorChar) {
                depth++;
            }
        }
        return depth;
    }

    private static String indent(int depth) {
        String res = "";
        for (int i = 0; i < depth; i++) {
            res += "--";
        }
        return res;
    }

}
