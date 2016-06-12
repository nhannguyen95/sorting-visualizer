package com.egovy.snsv;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import utils.Assets;
import utils.Constants;

import java.util.ArrayList;

/**
 * Created by apple on 3/15/16.
 */
public class ElementManager {

    ArrayList<Element> elems;
    ArrayList<String> instructions;

    private boolean launchIns;
    private boolean isPaused;
    private float cntTime;
    private int curStep;
    int[] preVals, curVals;
    private int offsetHeight;
    private int delta = 18;
    private int elemWidth;
    private Color defaultColor;
    private int maxArrayElement;
    ElementManager countSlotManager;

    class Point {
        float x;
        float y;

        public Point(float x, float y) { this.x = x; this.y = y; }
    }

    public ElementManager(int maxArrayElement, int offsetHeight, Color defaultColor, ElementManager countSlotManager) {
        this.offsetHeight = offsetHeight;
        this.defaultColor = defaultColor;
        this.maxArrayElement = maxArrayElement;
        this.countSlotManager = countSlotManager;
        preVals = null;
        curVals = null;
        elems = new ArrayList<Element>();
        for (int i = 0; i < maxArrayElement; i++) {
            elems.add(new Element(Assets.instance.base));
        }
        elemWidth = (int) elems.get(0).img.getWidth();

        instructions = null;
        launchIns = false;
        isPaused = false;
        cntTime = 0.0f;
        curStep = -1;
    }

    public Table getArrayTable() {
        Table table = new Table();

        for (int i = 0; i < elems.size(); i++) {
            Element e = elems.get(i);
            table.addActor(e.img);
            e.img.setPosition(-e.img.getWidth() / 2.0f, offsetHeight);
            e.updateOldPos();
            e.img.setColor(defaultColor);
            e.img.addAction(Actions.fadeOut(0));
        }

        return table;
    }

    public void showFirstTime() {

        int xOfFirstElem = calcXOfFirstElem();

        for (int i = 0; i < this.maxArrayElement; i++) {
            Element e = elems.get(i);
            Image img = e.img;
            img.setPosition(xOfFirstElem + i * (elemWidth + delta), 0 + offsetHeight);
            e.updateOldPos();
            if (i < curVals.length) {
                img.addAction(Actions.sequence(Actions.fadeIn(0.1f + i * 0.08f),
                        Actions.sizeTo(elemWidth, curVals[i] + 30, 0.4f),
                        Actions.sizeTo(elemWidth, curVals[i], 0.12f)));
            }
        }
    }

    public void hide() {
        for(Element elem : elems) {
            elem.img.addAction(Actions.fadeOut(0.5f));
        }
    }

    public void showCurTime() {

        int xOfFirstElem = calcXOfFirstElem();

        int m = preVals.length;
        int n = curVals.length;

        if (n >= m) {
            for (int i = 0; i < this.maxArrayElement; i++) {
                Element e = elems.get(i);
                Image img = e.img;
                if (i < n) {
                    img.addAction(Actions.parallel(
                            Actions.color(defaultColor, 0.5f),
                            Actions.moveTo(xOfFirstElem + i * (elemWidth + delta), img.getY(), 0.5f),
                            Actions.sizeTo(elemWidth, curVals[i], 0.5f)));
                    e.updateOldPos(xOfFirstElem + i * (elemWidth + delta), img.getY());
                } else {
                    img.addAction(Actions.moveTo(xOfFirstElem + i * (elemWidth + delta), img.getY(), 0.5f));
                    e.updateOldPos(xOfFirstElem + i * (elemWidth + delta), img.getY());
                }
            }
        } else { // n < m
            for (int i = 0; i < this.maxArrayElement; i++) {
                Element e = elems.get(i);
                Image img = e.img;
                if (i < n) {
                    img.addAction(Actions.parallel(
                            Actions.color(defaultColor, 0.5f),
                            Actions.moveTo(xOfFirstElem + i * (elemWidth + delta), img.getY(), 0.5f),
                            Actions.sizeTo(elemWidth, curVals[i], 0.5f)));
                    e.updateOldPos(xOfFirstElem + i * (elemWidth + delta), img.getY());
                } else {
                    if (i < m) img.addAction(Actions.fadeOut(0.1f));
                    img.addAction(Actions.moveTo(xOfFirstElem + i * (elemWidth + delta), img.getY(), 0.5f));
                    e.updateOldPos(xOfFirstElem + i * (elemWidth + delta), img.getY());
                }
            }
        }
    }

    private int calcXOfFirstElem() {
        int n = curVals.length;
        int xOfFirstElem = 0;
        if (n % 2 == 0) {
            xOfFirstElem = -(n / 2 * elemWidth + (n / 2 - 1) * delta + delta / 2);
        } else {
            xOfFirstElem = -(n / 2 * elemWidth + n / 2 * delta + elemWidth / 2);
        }
        return xOfFirstElem;
    }

    private void takeAction(String ins) {
        String[] items = ins.split(" ");

        String pre = items[0];
        if (pre.equals("MVB")) {
            Element esrc = elems.get(Integer.parseInt(items[1]));
            Element edes = elems.get(Integer.parseInt(items[2]));
            Element eslot = countSlotManager.getElems().get(Integer.parseInt(items[3]));
            Image isrc = esrc.img;
            Image ides = edes.img;
            Image islot = eslot.img;

            isrc.addAction(Actions.moveTo(edes.oldPos.x, edes.oldPos.y, Constants.REAL_TIME_PER_ACTION));
            eslot.amt--;
            islot.addAction(Actions.color(Constants.COUNT_SLOT_COLOR_ARRAY[
                            edes.amt > Constants.COUNT_SLOT_COLOR_ARRAY.length - 1? Constants.COUNT_SLOT_COLOR_ARRAY.length-1 : edes.amt],
                    Constants.REAL_TIME_PER_ACTION));

        } else if (pre.equals("MVC")) {
            Element esrc = elems.get(Integer.parseInt(items[1]));
            Element edes = countSlotManager.getElems().get(Integer.parseInt(items[2]));
            Image isrc = esrc.img;
            Image ides = edes.img;

            isrc.addAction(Actions.moveTo(ides.getX(), ides.getY(), Constants.REAL_TIME_PER_ACTION));
            edes.amt++;
            ides.addAction(Actions.color(Constants.COUNT_SLOT_COLOR_ARRAY[
                    edes.amt > Constants.COUNT_SLOT_COLOR_ARRAY.length - 1 ? Constants.COUNT_SLOT_COLOR_ARRAY.length-1 : edes.amt],
                    Constants.REAL_TIME_PER_ACTION));
        } else if (pre.equals("UHLHLG")) {
            elems.get(Integer.parseInt(items[1])).img.addAction(
                    Actions.color(defaultColor, Constants.REAL_TIME_PER_ACTION));
            elems.get(Integer.parseInt(items[2])).img.addAction(
                    Actions.color(Constants.GREEN_COLOR, Constants.REAL_TIME_PER_ACTION));
        }
        else if (pre.equals("UHLHLO")) {
            elems.get(Integer.parseInt(items[1])).img.addAction(
                    Actions.color(defaultColor, Constants.REAL_TIME_PER_ACTION));
            elems.get(Integer.parseInt(items[2])).img.addAction(
                    Actions.color(Constants.ORANGE_COLOR, Constants.REAL_TIME_PER_ACTION));
        } else if (pre.equals("UHLHLR")) {
            elems.get(Integer.parseInt(items[1])).img.addAction(
                    Actions.color(defaultColor, Constants.REAL_TIME_PER_ACTION));
            elems.get(Integer.parseInt(items[2])).img.addAction(
                    Actions.color(Constants.RED_COLOR, Constants.REAL_TIME_PER_ACTION));
        } else if (pre.equals("HLBHLO")) {
            elems.get(Integer.parseInt(items[1])).img.addAction(
                    Actions.color(Constants.BLUE_COLOR, Constants.REAL_TIME_PER_ACTION));
            elems.get(Integer.parseInt(items[2])).img.addAction(
                    Actions.color(Constants.ORANGE_COLOR, Constants.REAL_TIME_PER_ACTION));
        } else if (pre.equals("HLR")) {
            elems.get(Integer.parseInt(items[1])).img.addAction(
                    Actions.color(Constants.RED_COLOR, Constants.REAL_TIME_PER_ACTION));
            if (items.length == 3) {
                elems.get(Integer.parseInt(items[2])).img.addAction(
                        Actions.color(Constants.RED_COLOR, Constants.REAL_TIME_PER_ACTION));
            }
        }
        else if (pre.equals("HLO")) {
            elems.get(Integer.parseInt(items[1])).img.addAction(
                    Actions.color(Constants.ORANGE_COLOR, Constants.REAL_TIME_PER_ACTION));
            if (items.length == 3) {
                elems.get(Integer.parseInt(items[2])).img.addAction(
                        Actions.color(Constants.ORANGE_COLOR, Constants.REAL_TIME_PER_ACTION));
            }

        } else if (pre.equals("HLG")) {
            for(int i = 1; i < items.length; i++) {
                elems.get(Integer.parseInt(items[i])).img.addAction(
                        Actions.color(Constants.GREEN_COLOR, Constants.REAL_TIME_PER_ACTION));
            }

        } else if (pre.equals("HLB")) {
          for(int i = 1; i < items.length; i++) {
              elems.get(Integer.parseInt(items[i])).img.addAction(
                      Actions.color(Constants.BLUE_COLOR, Constants.REAL_TIME_PER_ACTION));
          }

        } else if (pre.equals("UHL")) {
            for(int i = 1; i < items.length; i++) {
                elems.get(Integer.parseInt(items[i])).img.addAction(
                        Actions.color(defaultColor, Constants.REAL_TIME_PER_ACTION));
            }

        } else if (pre.equals("SWN")) {
            int id1 = Integer.parseInt(items[1]);
            int id2 = Integer.parseInt(items[2]);

            // swap 2 elem
            Element tmp = elems.get(id1);
            elems.set(id1, elems.get(id2));
            elems.set(id2, tmp);
        } else if (pre.equals("SW")) {
            int id1 = Integer.parseInt(items[1]);
            int id2 = Integer.parseInt(items[2]);
            float x1 = elems.get(id1).img.getX();
            float x2 = elems.get(id2).img.getX();
            float y = elems.get(id1).img.getY();
            elems.get(id1).img.addAction(Actions.moveTo(x2, y, Constants.REAL_TIME_PER_ACTION));
            elems.get(id1).updateOldPos(x2, y);
            elems.get(id2).img.addAction(Actions.moveTo(x1, y, Constants.REAL_TIME_PER_ACTION));
            elems.get(id2).updateOldPos(x1, y);

            // swap 2 elem
            Element tmp = elems.get(id1);
            elems.set(id1, elems.get(id2));
            elems.set(id2, tmp);
        }
    }

    private void reset() {
        launchIns = false;
        curStep = -1;
        cntTime = 0;
        isPaused = false;
    }

    public void update(float deltaTime) {

        for(Element elem : elems) {
            elem.prePos.x = elem.img.getX();
            elem.prePos.y = elem.img.getY();
        }

        if (launchIns) {
            if (!isPaused) cntTime += deltaTime;
            if (cntTime >= Constants.TIME_PER_ACTION) {
                curStep++;
                cntTime = 0;
                if (curStep < instructions.size()) {
                    takeAction(instructions.get(curStep));
                } else {
                    reset();
                }
            }
        }
    }

    public void applyInstructions(ArrayList<String> ins) {
        this.instructions = ins;
        this.launchIns = true;
    }

    public void applyVals(int[] vals) {
        this.preVals = this.curVals;
        this.curVals = vals;
    }

    public int[] getCurVals() {
        return this.curVals;
    }

    public boolean isFinish() {
        return !this.launchIns;
    }

    public void setPaused(boolean b) {
        this.isPaused = b;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public ArrayList<Element> getElems() {
        return this.elems;
    }

    public class Element {
        public Image img;
        public int amt = 0; // for count slot
        public Point oldPos = new Point(0,0);
        public Point prePos = new Point(0,0);

        public Element(Texture texture) {
            this.img = new Image(new TextureRegionDrawable(new TextureRegion(texture)));
        }

        public void updateOldPos() {
            this.oldPos.x = img.getX();
            this.oldPos.y = img.getY();
        }

        public void updateOldPos(float x, float y) {
            this.oldPos.x = x;
            this.oldPos.y = y;
        }

        public boolean isPosDif() {
            return !(oldPos.x == img.getX() && oldPos.y == img.getY());
        }
    }
}
