package image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;



public class GreyImage {
	private int dimX;
	private int dimY;
	private int size;
	
	private short[] data;
	
	public GreyImage(int dimX, int dimY)
	{
		this.dimX = dimX;
		this.dimY = dimY;
		this.size = dimX * dimY;
		
		data = new short[size];
		
		for(int i=0; i<size; i++)
			data[i] = 0;
	}
	
	
	public GreyImage(int dimX, int dimY, short[] data)
	{
		this.size = dimX * dimY;
		if(this.size != data.length)
			throw new IllegalArgumentException();
		
		this.dimX = dimX;
		this.dimY = dimY;
		
		this.data = data;
	}
	
	public int getSizeX()
	{
		return this.dimX;
	}
	
	public int getSizeY()
	{
		return this.dimY;
	}
	
	public int getSizeData()
	{
		return this.size;
	}
	
	public boolean isPosValid(int x, int y)
	{
		return x >= 0 && y >= 0 && x<dimX && y<dimY;
	}
	
	public boolean isPosValid(int offset)
	{
		return offset >= 0 && offset < size;
	}
	
	public short getPixel(int x, int y)
	{
		if(!isPosValid(x, y))
			throw new IllegalArgumentException();
		
		return data[y*dimY + x];
	}
	
	public void setPixel(int x, int y, short pixel)
	{
		if(!isPosValid(x, y))
			throw new IllegalArgumentException();
		
		data[y*dimY + x] = pixel;
	}
	
	public short getPixel(int offset)
	{
		if(!isPosValid(offset))
			throw new IllegalArgumentException();
		
		return data[offset];
	}
	
	public void setPixel(int offset, short pixel)
	{
		if(!isPosValid(offset))
			throw new IllegalArgumentException();
		
		data[offset] = pixel;
	}
	
	public void negative(short maxValue)
	{
		for(int i=0; i<size; i++)
		{
			data[i] = (short) (maxValue - data[i]);
		}
	}
	
	public short getMin()
	{
		short min = data[0];
		for(short value : data)
		{
			if(value < min)
				min=value;
		}
		return min;
	}
	public short getMax()
	{
		short max = data[0];
		for(short value : data)
		{
			if(value > max)
				max=value;
		}
		return max;
	}
	
	public void adjustConstrast(short min, short max)
	{
		short origMin = getMin();
		short origMax = getMax();
		for(int i=0; i<size; i++)
		{
			data[i] = (short) (min + ((data[i] - origMin) * (max - min)) / (origMax - origMin));
		}
	}
	
	public static GreyImage loadPGM(String filename) throws FileNotFoundException,IOException
	{
		PGMFileIO file = new PGMFileIO(filename);
		file.readPGM();
		
		GreyImage res = new GreyImage(file.getSizeX(), file.getSizeY(), file.getData());
		return res;
	}
	
	public void save(String filename) throws IOException
	{
		PGMFileIO file = new PGMFileIO(filename);
		
		file.writePGM(this.dimX, this.dimY, this.data);
	}
	
	
	
}
