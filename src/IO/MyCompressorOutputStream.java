package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;
    public MyCompressorOutputStream(OutputStream out) {
        this.out=out;

    }

    @Override
    public void write(int b) throws IOException {

    }
    @Override
    public void write(byte[] b) throws IOException {
        ArrayList<Integer> a = new ArrayList<>();
        for(int i=0;i<b.length;i++)
        {
            int num=b[i];
            a.add(num);
        }
        ArrayList<Integer> a1 = new ArrayList<>();
        int counter=0;
        int j=0;
        int c=0;
        int check=0;
        while(counter != 3)
        {
            if((check = a.get(c)) == 3){
                counter++;
            }
            if((check = a.get(c)) != 3)
            {
                counter=0;
            }
            c++;
        }
        for(int k=0;k<c;k++)
        {
            a1.add(a.get(k));
        }
        int bin;
        if(a.get(c) == 1)
        {
            bin=1;
            a1.add(1);
            //c++;
        }
        else{
            bin=0;
            a1.add(0);
            //c++;
        }

        int index=c;
        int sum=0;
        int f=c;
        while(index < a.size())
        {
            if(a.get(index)==1)
            {
                while(a.get(index)==1)
                {
                    sum++;
                    if(sum==127)
                    {
                        a1.add(sum);
                        a1.add(0);
                        sum=0;
                    }
                    index++;
                    if(index==a.size())
                        break;
                }
                a1.add(sum);
                sum=0;
            }
            if(index==a.size())
                break;
            if(a.get(index)==0)
            {
                while(a.get(index)==0)
                {
                    sum++;
                    if(sum==127)
                    {
                        a1.add(sum);
                        a1.add(0);
                        sum=0;
                    }
                    index++;
                    if(index==a.size())
                        break;
                }
                a1.add(sum);
                sum=0;
            }

        }
        //System.out.println(a1);
        byte[] bb = new byte[a1.size()];
        for(int v=0;v<a1.size();v++)
        {
            int num = a1.get(v);
            out.write((byte)num);
            //bb[v]=(byte)num;
        }
        //System.out.println(a1);
        //System.out.println(Arrays.toString(bb));
    }
}
