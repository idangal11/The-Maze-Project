package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleDecompressorInputStream extends InputStream {
    InputStream in;
    public SimpleDecompressorInputStream(InputStream in)
    {
        this.in=in;
    }
    @Override
    public int read() throws IOException {
        return 0;
    }
    @Override
    public int read(byte[] b) throws IOException {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> arrSecond = new ArrayList<>();
        int read = in.read();

        while(read != -1)
        {
            arr.add(read);
            read=in.read();
        }
        //System.out.println(arr);
        int counter=0;
        int c=0;
        int check=0;

        while(counter != 3)
        {
            if((check = arr.get(c)) == 3){
                counter++;
            }
            if((check = arr.get(c)) != 3)
            {
                counter=0;
            }
            c++;
        }
        for(int k=0;k<c;k++)
        {
            arrSecond.add(arr.get(k));
        }
        int bin;
        if(arr.get(c) == 1)
        {
            bin=1;
        }
        else{
            bin=0;
        }
        c++;
        int index=c;
        while(index<arr.size())
        {
            for(int g=0;g<arr.get(index);g++)
            {
                arrSecond.add(bin);
            }
            index++;
            if(bin==1){bin=0;}
            else{bin=1;}

        }
        //System.out.println(arrSecond);
        for(int n=0;n<arrSecond.size();n++)
        {
            int num=arrSecond.get(n);
            b[n]=(byte)num;
        }
        //System.out.println(Arrays.toString(b));
        return 0;
    }
}
