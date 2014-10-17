/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package world1tradechatparser;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author aero
 */
public class World1TradeChatParser {

    /**
     * @param args the command line arguments
     */
    
    static BufferedImage pass = null;
    
    public static void main(String[] args) throws AWTException {
        // TODO code application logic here
        //https://www.youtube.com/user/MrOwnagePranks/videos
        Robot rob = new Robot();
        BufferedImage flag = null;
        try {
            flag = ImageIO.read(World1TradeChatParser.class.getResourceAsStream("/flagdown.png"));
        } catch (IOException ex) {
            Logger.getLogger(World1TradeChatParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage initial = rob.createScreenCapture(new Rectangle(0,0,1920,1080));
        
        int topc = flag.getRGB(0, 0);
        
        boolean found = false;
        
        int fx = 0;
        int fy = 0;
        
        for(int x = 0; x < 1920; x++){
            for(int y = 0; y < 1080; y++){
                int col = initial.getRGB(x, y);
                if(!found && col == topc){
                    fx = x;
                    fy = y;
                    System.out.println("Possible match at " + fx + ", " + fy);
                    boolean match = true;
                    for(int sx = 0; sx < flag.getWidth(); sx++){
                        for(int sy = 0; sy < flag.getHeight(); sy++){
                            if(initial.getRGB(x+sx,y+sy) != flag.getRGB(sx, sy)){
                                match = false;
                                break;
                            }
                        }
                        if(!match) break;
                    }
                    if(match)found = true;
                }
            }
        }
        
        if(!found){
            System.out.println("ERR: Could not locate chat!");
            System.exit(0);
        }
        
        //496,120
        
        
        System.out.println("Found " + fx + ", " + fy);
        
        Font fon = null;
        
        try {
            fon = Font.createFont(Font.TRUETYPE_FONT, World1TradeChatParser.class.getResourceAsStream("/RuneScape-Chat-07.ttf")).deriveFont(16f);
        } catch (FontFormatException ex) {
            Logger.getLogger(World1TradeChatParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(World1TradeChatParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        final BufferedImage[] chars = new BufferedImage[256];
        final CChar[] cchars = new CChar[256];
        
        
        BufferedImage tmp = new BufferedImage(14,14,BufferedImage.TYPE_INT_ARGB);
        Graphics2D gtmp = tmp.createGraphics();
        
        gtmp.setFont(fon);
        
        for(int i = 0; i < 256; i++){
            gtmp.setColor(Color.BLACK);
            gtmp.fillRect(0,0,14,14);
            gtmp.setColor(Color.WHITE);
            gtmp.drawString(String.valueOf((char)i),0,13);
            boolean seen = false;
            boolean everseen = false;
            int first = 0;
            int last = 0;
            for(int x = 0; x < 14; x++){
//                System.out.println(tmp.getRGB(0,0));
                boolean has = false;
                for(int y = 0; y < 14; y++)if(tmp.getRGB(x, y) != -16777216){has = true; break;}
                if(has && !everseen){
                    first = x;
                    everseen = true;
                }
                if(!has && everseen){
                    last = x;
                    break;
                }
            }
            if(last-first == 0)last++;
            chars[i] = new BufferedImage(last-first,14,BufferedImage.TYPE_INT_ARGB);
            chars[i].createGraphics().drawImage(tmp,-first,0, null);
            cchars[i] = new CChar(chars[i]);
//            try {
//                ImageIO.write(chars[i], "PNG", new File("/Users/aero/iconsadjusted/" + i + ".png"));
//            } catch (IOException aex) {
//                Logger.getLogger(World1TradeChatParser.class.getName()).log(Level.SEVERE, null, aex);
//            }
//            int ox = 0;
//            int ex = 0;
//            for(int x = 0; x < 14; x++){
//                seen = false;
//                for(int y = 0; y < 14; y++){
//                    Color c = new Color(tmp.getRGB(x, y));
//                    int totes = c.getRed()+c.getGreen()+c.getBlue();
//                    if(totes>32){
//                        seen = true;
//                        everseen = true;
//                        break;
//                    }
//                }
//                if(!seen && !everseen)ox = x;
//                if(!seen && everseen){ex=x;break;}
//            }

        }
            
        
        final JFrame j = new JFrame("wand");
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                j.add(new Component() {
                    
                    
                    BufferedImage[] srsegs = new BufferedImage[8];
                    Graphics2D[] gsegs = new Graphics2D[8];
                    
                    BufferedImage[] finalsegs = new BufferedImage[8];
                    Graphics2D[] fgsegs = new Graphics2D[8];
                    
                    BufferedImage[] compsegs = new BufferedImage[8];
                    Graphics2D[] cgsegs = new Graphics2D[8];
                    
                    String[] stringsegs = new String[8];
                
                    {
                        for(int i = 0; i < 8; i++){
                            srsegs[i] = new BufferedImage(496-11, 14,BufferedImage.TYPE_BYTE_BINARY);
                            gsegs[i] = srsegs[i].createGraphics();
                            finalsegs[i] = new BufferedImage(496-11, 14,BufferedImage.TYPE_INT_RGB);
                            fgsegs[i] = finalsegs[i].createGraphics();
                            compsegs[i] = new BufferedImage(496-11, 14,BufferedImage.TYPE_INT_RGB);
                            cgsegs[i] = compsegs[i].createGraphics();
                            stringsegs[i] = "--";
                        }
                        setSize(496-11,(120-7)*8);
                        setPreferredSize(new Dimension(496-11,(120-7)*8));
                    }
                    
                    BufferedImage[] segs = new BufferedImage[8];
                    int first16 = 0;
                    ArrayList<String> poisons = new ArrayList<String>();
                    ArrayList<String> sells = new ArrayList<String>();
                    ArrayList<String> buys = new ArrayList<String>();
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(Color.BLACK);
                        g.fillRect(0,0,800,1080);
                        for(int i = 0; i < 8; i++){
                            stringsegs[i] = "";
                            segs[i] = pass.getSubimage(0, i*14, 496-11, 14);
                            gsegs[i].drawImage(segs[i],0,0,null);
                            Graphics2D t = fgsegs[i];
                            Graphics2D t2 = cgsegs[i];
                            int lastseen = 0;
                            int lastseenstart = 0;
                            boolean contained = false;
                            boolean linelast = false;
                            boolean haschar = false;
                            t2.setColor(Color.WHITE);
                            for(int x = 0; x < 496-11; x++){
                                contained = false;
                                for(int y = 1; y < 13; y++){
                                    Color c = new Color(segs[i].getRGB(x, y));
                                    int totes = c.getRed() + c.getGreen() + c.getBlue();
                                    int blue = c.getBlue();
                                    if(totes<8){
                                        t.setColor(Color.WHITE);
                                        t2.setColor(Color.WHITE);
                                        contained = true;
                                        if(!haschar)lastseenstart = x;
                                        haschar = true;
                                    }else if(blue>128 && totes-blue<32){
                                        t.setColor(Color.YELLOW);
                                        t2.setColor(Color.WHITE);
                                        contained = true;
                                        if(!haschar)lastseenstart = x;
                                        haschar = true;
                                    }else{
                                        t.setColor(Color.BLACK);
                                        t2.setColor(Color.BLACK);
                                    }
                                    t.drawLine(x,y,x,y);
                                    t2.drawLine(x,y,x,y);
                                }
                                if(haschar && !contained){
//                                    ImageIO.write()
//                                    if(lastseenstart != lastseen){
                                        System.out.println("char " + lastseenstart + "-" + lastseen);
                                        BufferedImage chari = compsegs[i].getSubimage(lastseenstart, 0, 1+lastseen-lastseenstart, 14);
                                        CChar cc = new CChar(chari);
                                        int fid = 0;
                                        for(int fi = 32; fi < 255; fi++){
                                            if(cc.equals(cchars[fi])){
                                                fid = fi;
                                                break;
                                            }
                                        }
                                        if(fid != 0){
                                            stringsegs[i] += (char)fid;
                                        }else{
                                            stringsegs[i] += "*";
                                        }
//                                        if(first16++<16){
//                                            try {
//                                                ImageIO.write(chari, "PNG", new File("/Users/aero/iconsfromgame/" + first16 + ".png"));
//                                            } catch (IOException ex) {
//                                                Logger.getLogger(World1TradeChatParser.class.getName()).log(Level.SEVERE, null, ex);
//                                            }
//                                        }
//                                    }
                                    haschar = false;
//                                    cgsegs[i].drawImage(segs[i].getSubimage(lastseenstart, 0, 14, 14), 0,0,null);
                                }
                                if(contained) lastseen = x;
                                if(lastseen != x){
                                    if(x-lastseen>2)
                                        t.setColor(Color.CYAN);
                                    else
                                        t.setColor(Color.RED);
                                    t.drawLine(x,0,x,14);
                                }
                                if(x-lastseen==3){
                                    t.setColor(Color.CYAN);
                                    stringsegs[i] += " "; // space
                                    for(int ie = lastseen+1; ie < x; ie++){
                                        t.drawLine(ie, 0, ie, 14);
                                    }
                                }
                            }
                            String ta = stringsegs[i].toLowerCase();
                            if(ta.contains("glory") || ta.contains("wealth")){
//                            if(ta.contains("tar")||ta.contains("pot") || ta.contains("poi") || ta.contains("(s)") || ta.contains("\\(s\\)") || ta.contains("wep")){
                                if(!poisons.contains(stringsegs[i]))poisons.add(stringsegs[i]);
                                if(poisons.size()>4)poisons.remove(0);
                            }
                            if(ta.contains("buy")||ta.contains("wtb")||ta.contains("[b]")||ta.contains("\\[b\\]")){
                                if(!buys.contains(stringsegs[i]))buys.add(stringsegs[i]);
                                if(buys.size()>5)buys.remove(0);
                            }
                            if(ta.contains("sell")||ta.contains("wts")||ta.contains("[s]")||ta.contains("\\[s\\]")){
                                if(!sells.contains(stringsegs[i]))sells.add(stringsegs[i]);
                                if(sells.size()>5)sells.remove(0);
                            }
                        }
                        g.drawImage(pass,0,0,null);
                        g.setColor(Color.CYAN);
                        for(int i = 0; i < 8; i++){
                            g.drawImage(segs[i], 0, (120-7) + i * 16, null);
//                            g.drawImage(srsegs[i], 0, ((120-7)*2+16) + i * 16, null);
                            g.drawImage(compsegs[i], 0, ((120-7)*2+16) + i * 16, null);
                            g.drawImage(finalsegs[i], 0, ((120-7)*3+32) + i * 16, null);
                            g.drawString(stringsegs[i], 0, ((120-7)*4+58) + i * 16);

                        }
//                        g.setColor(Color.BLACK);
//                        g.fillRect(0, ((120-7)*4+58), 0, 600);
                        g.setColor(Color.YELLOW);
                        int of = 0;
                        for(int e = of; e < poisons.size()-of; e++){
                            g.drawString(poisons.get(e-of), 0, ((120-7)*5+70) + e * 16);
                        }
                        g.setColor(Color.RED);

                        for(int e = of; e < buys.size()-of; e++){
                            g.drawString(buys.get(e-of), 0, (int) (((120-7)*5.5f+86) + e * 16));
                        }
                        g.setColor(Color.GREEN);

                        for(int e = of; e < sells.size()-of; e++){
                            g.drawString(sells.get(e-of), 0, ((120-7)*6+112) + e * 16);
                        }
                    }
                
                });
                j.pack();
                j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                j.setVisible(true);
                
            }
            
        }).start();
        
        while(true){
            pass = rob.createScreenCapture(new Rectangle(fx+11, fy+7, 496-11,120-7));
            j.repaint();
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(World1TradeChatParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
