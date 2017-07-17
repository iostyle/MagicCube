package iostyle.com.magiccube.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class Pixel implements Serializable {
    int Red;
    int Greean;
    int Blue;
    String Color = "";

    public Pixel(int red, int greean, int blue) {
        Red = red;
        Greean = greean;
        Blue = blue;
        makeColor(this);
    }

    /**
     * 将RGB值转换为文字的方法 即用作显示，也用作魔方还原数据
     *
     * 需要注意的是 由于光线 魔方型号 等等因素干扰
     * 这里面的数字你可以自己进行调整
     *
     *
     */

    private void makeColor(Pixel p) {
        if (p.Red > 120) {
            if (p.Greean > 160) {
                if (p.Blue > 150) {
                    this.Color = "白";
                } else {
                    this.Color = "黄";
                }
            } else if (p.Greean > 50) {
                this.Color = "橙";
            } else {
                this.Color = "红";
            }
        } else {
            if (p.Greean > p.Blue) {
                this.Color = "绿";
            } else {
                this.Color = "蓝";
            }
        }
    }

    public int getRed() {
        return Red;
    }

    public void setRed(int red) {
        Red = red;
    }

    public int getGreean() {
        return Greean;
    }

    public void setGreean(int greean) {
        Greean = greean;
    }

    public int getBlue() {
        return Blue;
    }

    public void setBlue(int blue) {
        Blue = blue;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "R:" + Red +
                ",G:" + Greean +
                ",B:" + Blue + "  ";
    }

}
