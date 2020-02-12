package core;

import core.algorithms.HuffmanCore;
import core.algorithms.Util;
import core.animation.console;

import java.util.*;

public class main {
    
    private static Scanner sc = new Scanner(System.in);
    
    private static final String SOURCE_DIR = "text/source/";
    private static final String INTERMEDIATE_DIR = "text/intermediate/";
    private static final String FINAL_DIR = "text/final/";
    
    public static void compress(String fileName) throws Exception
    {
        HuffmanCore huff = new HuffmanCore();
        
        
        
        huff.loadFile(SOURCE_DIR + fileName);
        huff.generateFrequencyTable();
        huff.generateRoot();
        huff.encode();
        huff.writeBitCodeToFile(INTERMEDIATE_DIR + (fileName.split(".txt")[0]) + ".lmao");
        huff.writeFreqTableToFile(INTERMEDIATE_DIR + (fileName.split(".txt")[0]) + "-bin.txt");
        
        
        
        huff.readFreqTableToMemory(INTERMEDIATE_DIR + (fileName.split(".txt")[0]) + "-bin.txt");
        huff.recover();
        huff.decode(INTERMEDIATE_DIR + (fileName.split(".txt")[0]) + ".lmao");
        huff.writeFinalToFile(FINAL_DIR + fileName);
    }
    
    public static void open(String fileName) throws Exception
    {
        ///.runHuffmanUnpacking(fileName);
    }
    
    
    public static void run()
    {
        while(true)
        {
            String command = sc.nextLine();
            
            try
            {
                if (command.equals("stop"))
                {
                    break;
                }
                else if (command.equals("compress"))
                {
                    System.out.println("Which file do you wish to compress?");
                    command = sc.nextLine();
                    compress(command);
                }
                else if (command.equals("open"))
                {
                    System.out.println("which file do you wish to open?");
                    command = sc.nextLine();
                    
                    open(command);

                }
                else
                {
                    console.println("INVALID..");
                }
                
            }
            catch (Exception e)
            {
                System.out.println(e.toString());
            }
            
            
        }
    }
    
    public static void main(String[] args) throws Exception{
        
        console.println(console.APP_START);
        
        //compress("dream.txt");
        
        //Huffman h = new Huffman("ttttttttuuuuuuuuiiiiii t t t t t t\n");
        run();
        
        console.println(console.APP_END);
    }
}
