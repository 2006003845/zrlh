package javax.microedition.lcdui;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Region;

public class Graphics
{
	

	
	public static final int TRANS_NONE          = 0;
	public static final int TRANS_ROT90         = 5;
	public static final int TRANS_ROT180        = 3;
	public static final int TRANS_ROT270        = 6;
	public static final int TRANS_MIRROR        = 2;
	public static final int TRANS_MIRROR_ROT90  = 7;
	public static final int TRANS_MIRROR_ROT180 = 1;
	public static final int TRANS_MIRROR_ROT270 = 4;
	
	
    public static final int BASELINE        = 64;
    public static final int BOTTOM          = 32;
    public static final int LEFT            = 4;
    public static final int RIGHT           = 8;
    public static final int TOP             = 16;
    public static final int VCENTER         = 2;
    public static final int HCENTER         = 1;
    
    public static final int DOTTED          = 1;
    public static final int SOLID           = 0;
    
    private android.graphics.Canvas canvas;
    private Bitmap bitmap;
    private javax.microedition.lcdui.Font font;
    private Paint paint;
    private Paint outlinePaint;
    
    private int mTransX;
    private int mTransY;
    private Matrix ctm = new Matrix();
    public Graphics(android.graphics.Canvas canvas) 
    {
    	setFont(Font.getDefaultFont());
    	paint = new Paint();
    	paint.setStyle(Style.FILL);
    	outlinePaint = new Paint(this.paint);
    	outlinePaint.setStyle( Style.STROKE);
    	this.canvas = canvas;
    	canvas.save();
    }
   public static Graphics g;
    public static Graphics getGraphics(Canvas canvas)
	{
		if(g==null)
		{
			g=new Graphics(canvas);
		}
		return g;
	}
    
    private final Rect srcRect = new Rect();
	private final Rect dstRect = new Rect();
	private final RectF srcRectF = new RectF();

	private int mBitPosX;
	private int mBitPosY;
    /**
     * Create a Graphics with bitmap.Canvas only support a mutable bitmap
     * @param bitmap
     */
    public Graphics( Bitmap bitmap )
    {
    	this( new android.graphics.Canvas( bitmap ), bitmap );
    }
    
    public Graphics( android.graphics.Canvas canvas, Bitmap bitmap )
    {
        setFont( Font.getDefaultFont() );
        this.canvas = canvas;
        this.bitmap = bitmap;
        this.canvas.setBitmap(bitmap);
        paint = new Paint();
        paint.setStyle(Style.FILL);
    	outlinePaint = new Paint( this.paint );
    	outlinePaint.setStyle( Style.STROKE );
        canvas.save();
    }
    
    public Bitmap getBitmap()
    {
    	return this.bitmap;
    }
    
    public android.graphics.Canvas getCanvas()
    {
    	canvas.setDrawFilter(new PaintFlagsDrawFilter
    			(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
    	return this.canvas;
    }
    public void setCanvas(Canvas ca)
    {
    	this.canvas = ca;
    }
    
    /**
	 * Returns the X offset of the current clipping area, relative to the
	 * coordinate system origin of this graphics context.
	 */
	public int getClipX() {
		this.canvas.getClipBounds(srcRect);
		return srcRect.left;
	}

	/**
	 * Returns the Y offset of the current clipping area, relative to the
	 * coordinate system origin of this graphics context.
	 */
	public int getClipY() {
		this.canvas.getClipBounds(srcRect);
		return srcRect.top;
	}

	/**
	 * Returns the width of the current clipping area.
	 */
	public int getClipWidth() {
		if (this.canvas.getClipBounds(srcRect))
			return srcRect.width();
		return this.canvas.getWidth();
	}

	/**
	 * Returns the height of the current clipping area.
	 */
	public int getClipHeight() {
		if (this.canvas.getClipBounds(srcRect))
			return srcRect.height();
		return this.canvas.getHeight();
	}

    
    public int getColor()
    {
        return this.paint.getColor() & 0x00FFFFFF;
    }
    /**
     * set Color of Graphic to draw 2D figure.
     * No alpha channel in palette.
     * @param color
     */
    public void setColor( int color )
    {
        this.paint.setColor( 0xFF000000 | color );
    }
    public void setARGBColor(int color)
    {
    	this.paint.setColor( color );
    }
    public void setColor(int r, int g, int b) 
         {  
    	int val = (r<<16)|(g<<8)|b;
    	setColor(val);
    } 
    public void fillRect( int x, int y, int width, int height )
    {
        this.canvas.drawRect( x, y, x+width, y+height, this.paint );
    }
    public void fillRect( int x, int y, int width, int height,int color )
    {
		this.paint.setColor(color);
        this.canvas.drawRect( x, y, x+width, y+height, this.paint );
    }
    
    public void fillRoundRect( int x, int y, int width, int height, int rx, int ry ) {
    	this.canvas.drawRoundRect( new RectF( x, y, x+width, y+height ), rx, ry, this.paint );
    }
    
    public void drawImage( javax.microedition.lcdui.Image image, int x, int y, int anchor )
    {
    	getBitmapPos(image.getWidth(), image.getHeight(), x, y, anchor);
        this.canvas.drawBitmap( image.getBitmap(), mBitPosX, mBitPosY, null );
    }
    
    public void drawLine( int x1, int y1, int x2, int y2 )
    {
    	this.outlinePaint.setColor(this.paint.getColor());
        this.canvas.drawLine( x1, y1, x2, y2, this.outlinePaint );
    }
    
    public void drawRect( int x, int y, int width, int height )
    {
    	this.outlinePaint.setColor(this.paint.getColor());
        this.canvas.drawRect( x, y, x+width, y+height, outlinePaint );
    }
    
    public void drawRoundRect( int x, int y, int width, int height, int rx, int ry ) {
    	this.outlinePaint.setColor(this.paint.getColor());
        this.canvas.drawRoundRect( new RectF( x, y, x+width, y+height), rx, ry, outlinePaint );
    }
    
    public javax.microedition.lcdui.Font getFont()
    {
        return this.font;
    }
    
    public void setFont( javax.microedition.lcdui.Font font )
    {
        Paint typefacePaint = font.getTypefacePaint();
        if( this.paint != null )
        {
            this.paint.setTypeface( typefacePaint.getTypeface() );
            this.paint.setUnderlineText( typefacePaint.isUnderlineText() );
        }
        else
        {
            this.paint = new Paint( typefacePaint );
        }
        this.font = font;
    }
    public void drawString( String str, int x, int y, int anchor )
    {
        Align align;
        if( ( anchor & HCENTER ) != 0 )
        {
            align = Align.CENTER;
        }
        else if( ( anchor & RIGHT ) != 0 )
        {
            align = Align.RIGHT;
        }
        else
        {
            align = Align.LEFT;
        }
        if( ( anchor & TOP ) != 0 )
        {
            y+= 12;
        }
        else if( ( anchor & BASELINE ) != 0 )
        {
            y+= 5;
        }
        else
        {
        		y+= 0;
        }
        Paint paint = font.getTypefacePaint();
        paint.setTextSize(font.getScale() * 14);
        paint.setColor(this.paint.getColor());
        paint.setTextAlign( align );
        this.canvas.save();
//        this.canvas.scale( scale, scale );
        /*draw Font offet 8 pixels*/
        this.canvas.drawText( str, x, y, paint);
        this.canvas.restore();
    }
    
    public void clipRect( int x, int y, int w, int h ){
    	this.canvas.clipRect( x, y, x+w, y+h );
    }
    
    public void setClip( int x, int y, int w, int h )
    {
    	srcRect.set(x, y, x + w, y + h);
    	this.canvas.clipRect(srcRect, Region.Op.REPLACE);
	}
    
    public void fillArc( int x, int y, int width, int height, int startAngle, int arcAngle )
    {
    	srcRectF.set(x, y, x + width, y + height);
    	this.canvas.drawArc(srcRectF, startAngle+180, 360-arcAngle, true, this.paint);
    }
    
    
    public void translate( int x, int y )
    {
//   	this.mTransX += x;
//   	this.mTransY += y;
    	this.canvas.translate( x, y );
    }

//    public int getTranslateX() {
//    	return this.mTransX;
//    }
//    
//    public int getTranslateY() {
//    	return this.mTransY;
//    }
  /**
	 * Returns the X coordinate of the translated origin of this graphics context. 
	 */
	public int getTranslateX() {
		float org[] = new float[2];
		this.canvas.getMatrix(ctm);
		ctm.mapPoints(org);
		return (int)org[0];
	}

	/**
	 * Returns the Y coordinate of the translated origin of this graphics context. 
	 */
	public int getTranslateY() {
		float org[] = new float[2];
		this.canvas.getMatrix(ctm);
		ctm.mapPoints(org);
		return (int)org[1];
	}
	
	
	public void drawRegion(Image imgImage,int x_src,int y_src,int width,int height,int transform,int x_dest,int y_dest,int anchor,int alpha)
	{
		if(alpha == 255)
		{
			drawRegion(imgImage,x_src,y_src,width,height,transform,x_dest,y_dest,anchor);
		}
		else
		{
			paint.setAlpha(alpha);
			drawRegion(imgImage,x_src,y_src,width,height,transform,x_dest,y_dest,anchor);
			paint.setAlpha(255);
		}
		
	}

	public void drawRegion(Image imgImage, int x_src, int y_src, int width,
			int height, int transform, int x_dest, int y_dest, int anchor) 
	{
		srcRect.left = x_src;
		srcRect.top = y_src;
		srcRect.right = x_src + width;
		srcRect.bottom = y_src + height;

		dstRect.left = 0;
		dstRect.top = 0;
		dstRect.right = width;
		dstRect.bottom = height;
		this.canvas.save();
		switch (transform) 
		{
		case 0: // TRANS_NONE
			getBitmapPos(width, height, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX, mBitPosY);
			break;

		case TRANS_ROT90:
			getBitmapPos(height, width, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX + height, mBitPosY);
			this.canvas.rotate(90);
			break;

		case TRANS_ROT180:
			getBitmapPos(width, height, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX + width, mBitPosY + height);
			this.canvas.rotate(180);
			break;

		case TRANS_ROT270:
			getBitmapPos(height, width, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX, mBitPosY + width);
			this.canvas.rotate(270);
			break;

		case TRANS_MIRROR: // TRANS_MIRROR
			getBitmapPos(width, height, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX + width, mBitPosY);
			this.canvas.scale(-1, 1, 0, 0);
			break;

		case TRANS_MIRROR_ROT180: // TRANS_MIRROR_ROT180
			getBitmapPos(width, height, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX + width , mBitPosY + height);
			this.canvas.rotate(180);
			this.canvas.translate(width,0);
			this.canvas.scale(-1, 1, 0, 0);
			break;
		case TRANS_MIRROR_ROT270:
			getBitmapPos(height, width, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX, mBitPosY + width);
			this.canvas.rotate(270);
			this.canvas.translate(width,0);
			this.canvas.scale(-1, 1, 0, 0);
			break;
		case TRANS_MIRROR_ROT90:
			getBitmapPos(height, width, x_dest, y_dest, anchor);
			this.canvas.translate(mBitPosX + height, mBitPosY);
			this.canvas.rotate(90);
			this.canvas.translate(width,0);
			this.canvas.scale(-1, 1, 0, 0);
			break;
		}
		if(imgImage.getBitmap()!=null)
		this.canvas.drawBitmap(imgImage.getBitmap(), srcRect, dstRect, paint);
		this.canvas.restore();
	}

	public void copyArea(int xSrc, int ySrc, int width, int height, int xDest,
			int yDest, int anchor) {
		// TODO Auto-generated method stub
		
	}

	public void drawArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
		this.outlinePaint.setColor(this.paint.getColor());
    	srcRectF.set(x, y, x + width, y + height);
    	this.canvas.drawArc(srcRectF, startAngle, arcAngle, true, this.outlinePaint);	
	}

	public void drawChar(char character, int x, int y, int anchor) {
		
		drawString(String.valueOf(character),x,y,anchor);
	}

	public void drawChars(char[] data, int offset, int length, int x, int y,
			int anchor) {

		drawString(String.valueOf(data, offset, length),x,y,anchor);
	}

	public void drawRGB(int[] rgbData, int offset, int scanlength, int x,
			int y, int width, int height, boolean processAlpha) {
		this.canvas.drawBitmap(rgbData, offset, scanlength, x, y, width, height, processAlpha, null);
	}

	public void drawSubstring(String str, int offset, int len, int x, int y,
			int anchor) {
		this.drawString(str.substring(offset, len), x, y, anchor);
	}
	/**
	 * draw a Triangle not fill
	 * Notice:the triangle is not fill.drawVertices
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 */
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		this.canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 6, new float[] {x1, y1, x2, y2, x3, y3}, 0, null, 0, null, 0, null, 0, 0, paint);	
	}
	/**
	 * draw a Triangle with fill with Alpha
	 * Notice¡êoset paint with fill & Alpha not restore
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param x3
	 * @param y3
	 */
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3,int color) {
		this.paint.setColor(color);
		this.canvas.drawVertices(Canvas.VertexMode.TRIANGLES, 6, new float[] {x1, y1, x2, y2, x3, y3}, 0, null, 0, null, 0, null, 0, 0, paint);	
	}
	public int getBlueComponent() {
		
		return this.paint.getColor() & 0x000000FF;
	}

	public int getDisplayColor(int color) {
		return this.paint.getColor() & 0x00FFFFFF;
	}

	public int getGrayScale() {
		return 0;
	}

	public int getRedComponent() {
		return this.paint.getColor() & 0x00FF0000;
	}
	public int getGreenComponent() {
		return this.paint.getColor() & 0x0000FF00;
	}
	

	public int getStrokeStyle() {
		int rgbColor = this.paint.getColor();
		byte r = (byte) (0xFF &(rgbColor >> 4));
		byte g = (byte) (0xFF &(rgbColor >> 4));
		byte b = (byte) (0xFF &(rgbColor >> 4));
		if (r == g && g== b)
			return r;
		else
			return 0;
	}

	public void setGrayScale(int value) {
		if ((value < 0) || (value > 255)) {
			throw new IllegalArgumentException("Gray value out of range");
		}
		int rgbColor = (value << 16) | (value << 8) | value;
		this.paint.setColor(rgbColor);
	}

	private void getBitmapPos(int w, int h, int x, int y, int anchor) {
		if ((anchor & RIGHT) != 0) {
			mBitPosX = x - w;
		} else if ((anchor & HCENTER) != 0) {
			mBitPosX = x - (w >> 1);
		} else {
			mBitPosX = x;
		}

		if ((anchor & BOTTOM) != 0) 
		{
			mBitPosY = y - h;
		} else if ((anchor & VCENTER) != 0) {
			mBitPosY = y - (h >> 1);
		} else {
			mBitPosY = y;
		}
	}
	/**
	 * Sets the stroke style used for drawing. 
	 * could be effect in 2D fill & draw
	 * @param style
	 */
	public void setStrokeStyle(int style) {
		if(style == DOTTED)
		{
			this.paint.setStyle(Paint.Style.STROKE);
		}
		else if(style == SOLID)
		{
			this.paint.setStyle(Paint.Style.FILL);
		}
		
	}
}
