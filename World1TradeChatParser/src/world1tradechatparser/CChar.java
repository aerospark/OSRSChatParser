/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package world1tradechatparser;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 *
 * @author aero
 */
public class CChar {
    public int[] compcols = new int[256]; // needlessly large
    int w = 0;
    public CChar(BufferedImage b){
        w = b.getWidth();
        if(w>15)w=15;
        for(int x = 0; x < w; x++){
            for(int y = 1; y < b.getHeight()-1; y++){
                compcols[(x<<4|y)] = b.getRGB(x,y);
            }
        }
    }
    public boolean equals(CChar co){
        if(w != co.w) return false;
        return Arrays.equals(compcols, co.compcols);
    }
}
