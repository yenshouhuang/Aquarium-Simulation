
import java.io.*;
import java.util.*;
public class SwimSimulation {
    private PApplet processing;
    //畫魚的線，用畫的
    //    private Fish[] fish;
//    private Food[] food;
    ArrayList<SimObject> list;  // new
    //存食物、存魚
    //    private Hook[] hook;
    /*
     * This is a private helper method.
     * First read the file, FileOptions.ssf(include the path of data.ssf files), and then randomly choose the data.ssf for different number and initial position for fish, foods and hook
     *
     */
    private boolean initObjWithParaFile() {
        //初始化物件
        String relaPathAndSsfFileName = "FileOptions.ssf";
        try {
            Scanner input;
            String[] ssdRelaPathAndFiles = new String[1];
            try { // try to read FileOptions.ssf
                input = new Scanner(new File(relaPathAndSsfFileName));


            } catch (Exception ex) {
                System.out.println("WARNING: Could not find or open the FileOptions.ssf file.");
                System.out.println(ex.toString());
                return false;
            }

            String randomlyChosenPathFile = "";
            try { //translate input string into file path string
                while (input.hasNext()) {
                    String fileContent = input.nextLine();
                    fileContent = fileContent.replace('\\', File.separatorChar).replace('/', File.separatorChar);
                    ssdRelaPathAndFiles = fileContent.split(";");
                    for (int i = 0; i < ssdRelaPathAndFiles.length; i++) {
                        ssdRelaPathAndFiles[i] = ssdRelaPathAndFiles[i].trim();
                    }
                }
                input.close();
                // randomly choose a data.ssf file 
                randomlyChosenPathFile = ssdRelaPathAndFiles[Utility.randomInt(ssdRelaPathAndFiles.length)];
                input = new Scanner(new File(randomlyChosenPathFile));
                System.out.println(randomlyChosenPathFile);
            } catch (Exception ex) {
                System.out.println("WARNING: Could not find or open the " + randomlyChosenPathFile + " file.");
                System.out.println(ex.toString());
                return false;
            }
            String[] lineCompon = null;
            try { //try to handle the input string in chosen data.ssf file
                while (input.hasNext()) {
                    String line = input.nextLine();
                    lineCompon = line.split(":");

                    if (lineCompon == null || lineCompon[0] == null || lineCompon[0].trim().equals("")) {
                        continue;
                    } else { //lineCompon[0] is the object name, and lineCompon[1] is the number of object 
                        if (lineCompon[0].trim().toUpperCase().equals("FISH") || lineCompon[0].trim().toUpperCase().equals("FOOD") || lineCompon[0].trim().toUpperCase().equals("HOOK"))
                            ;
                        else {
                            System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                            return false;
                        }
                        int objectNo = Integer.parseInt(lineCompon[1].trim());
                        int objectType = 0;
                        if (lineCompon[0].trim().toUpperCase().equals("FISH")) {
                            //fish = new Fish[objectNo];
                            objectType = 1;
                        } else if (lineCompon[0].trim().toUpperCase().equals("FOOD")) {
                            //food = new Food[objectNo];
                            objectType = 2;
                        } else if (lineCompon[0].trim().toUpperCase().equals("HOOK")) {
                            //hook = new Hook[objectNo];
                            objectType = 3;
                        }
                        /*
                         * Try to read the initial position of object in data.ssf file
                         *  pass the initial position to object(hook, fish, and foods)
                         */
                        int posi_Counter = 0;
                        String[] posi_XY = null;
                        while (input.hasNext() && ((!(((posi_XY = (input.nextLine().split(",")))[0]).trim().toUpperCase().contains("FISH")))) &
                                (!posi_XY[0].trim().toUpperCase().contains("FOOD")) & (!posi_XY[0].trim().toUpperCase().contains("HOOK"))) {
                            if (posi_XY[0] == null || posi_XY[0].trim().equals(""))
                                ;
                            else {

                                int x = Integer.parseInt(posi_XY[0].trim());
                                int y = Integer.parseInt(posi_XY[1].trim());
                                if (posi_Counter < objectNo) {
                                    switch (objectType) {
                                        case 1:
                                            //fish[posi_Counter] = new Fish(/* processing, */ x, y);
                                            list.add(new Fish(x,y));
                                            break;
                                        case 2:
                                            //food[posi_Counter] = new Food(/* processing, */ x, y);
                                            list.add(new Food(x,y));
                                            break;
                                        case 3:
                                            //hook[posi_Counter] = new Hook(/* processing, */ x, y);
                                            list.add(new Hook(x,y));  // Attention     may unneeded!
                                            break;
                                    }
                                }
                                posi_Counter++;
                            }

                        }
                        if (posi_Counter == 0) {
                            System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                            return false;
                        }
                        if ((posi_Counter != objectNo)) { //the number of object is not equal to the position count in data.ssf file
                            System.out.println("WARNING: Number of " + lineCompon[0].trim() + " does not match number of " + lineCompon[0].trim() + " positions.");
                            return false;
                        }
                        // repeat the above code to handle the second object in data.ssf file
                        lineCompon = posi_XY[0].split(":");

                        objectNo = Integer.parseInt(lineCompon[1].trim());

                        if (lineCompon[0].trim().toUpperCase().equals("FISH")) {
                            //fish = new Fish[objectNo];
                            objectType = 1;
                        } else if (lineCompon[0].trim().toUpperCase().equals("FOOD")) {
                            //food = new Food[objectNo];
                            objectType = 2;
                        } else if (lineCompon[0].trim().toUpperCase().equals("HOOK")) {
                            //hook = new Hook[objectNo];
                            objectType = 3;
                        }


                        posi_Counter = 0;
                        posi_XY = null;
                        while (input.hasNext() && !(((posi_XY = (input.nextLine().split(",")))[0]).trim().toUpperCase().contains("FISH")) &
                                !posi_XY[0].trim().toUpperCase().contains("FOOD") & !posi_XY[0].trim().toUpperCase().contains("HOOK")) {
                            if (posi_XY[0].trim().equals(""))
                                ;
                            else {

                                int x = Integer.parseInt(posi_XY[0].trim());
                                int y = Integer.parseInt(posi_XY[1].trim());
                                if (posi_Counter < objectNo) {
                                    switch (objectType) {
                                        case 1:
                                            //fish[posi_Counter] = new Fish(/* processing, */ x, y);
                                            list.add(new Fish(x,y));
                                            break;
                                        case 2:
                                            //food[posi_Counter] = new Food(/* processing, */ x, y);
                                            list.add(new Food(x,y));
                                            break;
                                        case 3:
                                            //hook[posi_Counter] = new Hook(/* processing, */ x, y);
                                            list.add(new Hook(x,y));  // Attention     may unneeded!
                                            break;
                                    }
                                }
                                posi_Counter++;
                            }

                        }
                        if (posi_Counter == 0) {
                            System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                            return false;
                        }
                        if (posi_Counter != objectNo) {
                            System.out.println("WARNING: Number of " + lineCompon[0].trim() + " does not match number of " + lineCompon[0].trim() + " positions.");
                            return false;
                        }
                        //repeat above code to handle third object in data.ssf file 
                        lineCompon = posi_XY[0].split(":");
                        objectNo = Integer.parseInt(lineCompon[1].trim());
                        if (lineCompon[0].trim().toUpperCase().equals("FISH")) {
                            //fish = new Fish[objectNo];
                            objectType = 1;
                        } else if (lineCompon[0].trim().toUpperCase().equals("FOOD")) {
                            //food = new Food[objectNo];
                            objectType = 2;
                        } else if (lineCompon[0].trim().toUpperCase().equals("HOOK")) {
                            //hook = new Hook[objectNo];
                            objectType = 3;
                        }


                        posi_Counter = 0;
                        posi_XY = null;
                        while (input.hasNext()) {
                            String posi_Line = input.nextLine();
                            posi_XY = posi_Line.split(",");
                            if (posi_XY[0].trim().equals(""))
                                ;
                            else {
                                int x = Integer.parseInt(posi_XY[0].trim());
                                int y = Integer.parseInt(posi_XY[1].trim());
                                if (posi_Counter < objectNo) {
                                    switch (objectType) {
                                        case 1:
                                            //fish[posi_Counter] = new Fish(/* processing, */ x, y);
                                            list.add(new Fish(x,y));
                                            break;
                                        case 2:
                                            //food[posi_Counter] = new Food(/* processing, */ x, y);
                                            list.add(new Food(x,y));
                                            break;
                                        case 3:
                                            //hook[posi_Counter] = new Hook(/* processing, */ x, y);
                                            list.add(new Hook(x,y));  // Attention     may unneeded!
                                            break;
                                    }
                                }
                                posi_Counter++;
                            }

                        }
                        if (posi_Counter == 0) {
                            System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                            return false;
                        }
                        if (posi_Counter != objectNo) {
                            System.out.println("WARNING: Number of " + lineCompon[0].trim() + " does not match number of " + lineCompon[0].trim() + " positions.");
                            return false;
                        }
                    }
                }

                if (/*fish == null & food == null & hook == null*/ list.size() == 0) {

                    System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                    return false;
                }

            } catch (Exception ex) {
                System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                return false;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return false;
        }

        return true;

    }
    /*
     * swimSimulation constructor
     * to initialize the objects initial positions from fileOption.ssf file and data.ssf file
     * If cannot initialize positions from fileOption.ssf file and data.ssf file, then set default(fishNumber is four, hook number is one, and food number is six)

     */
    SwimSimulation(PApplet a_PApplet) {

//      processing = a_PApplet;
        SimObject.setProcessing(a_PApplet);
        //Fish.setHookAudio("audio\\lineapp_end_16k.wav");

        list = new ArrayList<SimObject>(); // new

        if (initObjWithParaFile()) {
            return;
        }


        list = new ArrayList<SimObject>(); // new

        int times = 4;       
        /*
        fish = new Fish[times];
        for (int i = 0; i < fish.length; i++) {
            fish[i] = new Fish( a_PApplet );
        }
        */
        for(int i = 0; i< times; i++)
        {
            list.add(new Fish());

        }

        times = 6;      
        /*
        food = new Food[times];
        for (int i = 0; i < food.length; i++) {
            food[i] = new Food( a_PApplet );
        }
        */
        for(int i = 0; i< times; i++)
        {
            list.add(new Food());
        }

        times = 1;
        /*
        hook = new Hook[times];
        for (int i = 0; i < hook.length; i++) {
            hook[i] = new Hook( a_PApplet );
        }
        */
        for(int i = 0; i< times; i++)
        {
            list.add(new Hook());
        }
    }
    public void update() {
        if(Utility.randomInt(20) == 7)   // lucky number 7,   1 in 20 chance,          new
        {
            list.add(new Food(Utility.randomInt(SimObject.processing.width) + 1,0));
        }


        //while (true) {
        // processing.background(0, 255, 255);
        SimObject.processing.background(0, 255, 255);
            
            /*
            for (int i = 0; i < fish.length; i++) { //every fish should try to eat every food 
                for (int j = 0; j < food.length; j++) {
                    fish[i].tryToEat(food[j]);
                }
            }
            for (int i = 0; i < hook.length; i++) { // every hook should try to catch every fish
                for (int j = 0; j < fish.length; j++) {
                    hook[i].tryToCatch(fish[j]);
                }
            }
            */
        for(int i =0; i < list.size(); i++)
            for(int j = 0; j < list.size(); j++)
                list.get(i).tryToInteract(list.get(j));
            // 物件互動
            /*
            for (int i = 0; i < fish.length; i++)
                fish[i].update();
            for (int i = 0; i < food.length; i++)  
                food[i].update();
            for (int i = 0; i < hook.length; i++)
                hook[i].update();
            break;
            */
        for(int i =0; i < list.size(); i++)
            list.get(i).update();
            //

        for(int i=0;i <list.size();i++){
            if (list.get(i).shouldBeRemoved())
            {
                list.remove(i);
                i--;
                //魚餌被魚吃到，把他刪除掉
            }

        }
    }
    public void handleClick(int mouseX, int mouseY) {
        // hook[0].handleClick(mouseX, mouseY);
        //
        for(int i=0;i <list.size();i++)
        {
            if (list.get(i) instanceof Hook)
            {
                ((Hook)list.get(i)).handleClick(mouseX, mouseY);
            }
        }

    }
}