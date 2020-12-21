import java.util.*;
import java.lang.*;

public class Main {
    private static SwimSimulation a_SwimSimulation;

    public static void main(String[] args) {
        Utility.startSimulation();
// 定時run 機制

    }

    public static void setup(Data data) {
        a_SwimSimulation = new SwimSimulation(data.processing);
    }
// fish and the blue area
    public static void update(Data data) {
        a_SwimSimulation.update();

    }

    public static void onClick(Data data, int mouseX, int mouseY)
    {
        a_SwimSimulation.handleClick(mouseX, mouseY);
// 按下滑鼠，method 就會被觸發，會看滑鼠X,Y 座標

    }
}
   