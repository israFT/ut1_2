package com.ift.ut1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ej2 {
    private static Scanner scan=new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        String file0="empleados_raf.dat",file1="copi_raf.dat";
        try(RandomAccessFile raf=new RandomAccessFile(file0, "rw");
            FileChannel fcReader= raf.getChannel();
        )
        {
            ByteBuffer bb= ByteBuffer.allocate(1024);
            Path copiado= Paths.get(file1);
            Set<StandardOpenOption> opciones= new HashSet<>();
            opciones.add(StandardOpenOption.CREATE);
            opciones.add(StandardOpenOption.APPEND);
            FileChannel fcWriter= FileChannel.open(copiado,opciones);
            while(fcReader.read(bb)>0)
            {
                bb.flip();
                fcWriter.write(bb);
            }
            bb.rewind();
            fcWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
