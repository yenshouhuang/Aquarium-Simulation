package com.thomas;

import java.io.*;  
import java.util.*;
public class SwimSimulation {
    private PApplet processing;

    ArrayList<SimObject> list;  // new

    private boolean initObjWithParaFile() {
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
                    } else {
                        if (lineCompon[0].trim().toUpperCase().equals("FISH") || lineCompon[0].trim().toUpperCase().equals("FOOD") || lineCompon[0].trim().toUpperCase().equals("HOOK"))
                        ;
                        else {
                            System.out.println("WARNING: Missing specification for the number and initial positions of fishes, foods, or hook.");
                            return false;
                        }
                        int objectNo = Integer.parseInt(lineCompon[1].trim());
                        int objectType = 0;
                        if (lineCompon[0].trim().toUpperCase().equals("FISH")) {

                            objectType = 1;
                        } else if (lineCompon[0].trim().toUpperCase().equals("FOOD")) {

                            objectType = 2;
                        } else if (lineCompon[0].trim().toUpperCase().equals("HOOK")) {

                            objectType = 3;
                        }

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

                                        	list.add(new Fish(x,y));
                                            break;
                                        case 2:

                                        	list.add(new Food(x,y));
                                            break;
                                        case 3:

                                        	list.add(new Hook(x,y));
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
                        if ((posi_Counter != objectNo)) {
                            System.out.println("WARNING: Number of " + lineCompon[0].trim() + " does not match number of " + lineCompon[0].trim() + " positions.");
                            return false;
                        }
                        // repeat the above code to handle the second object in data.ssf file
                        lineCompon = posi_XY[0].split(":");

                        objectNo = Integer.parseInt(lineCompon[1].trim());

                        if (lineCompon[0].trim().toUpperCase().equals("FISH")) {

                            objectType = 1;
                        } else if (lineCompon[0].trim().toUpperCase().equals("FOOD")) {

                            objectType = 2;
                        } else if (lineCompon[0].trim().toUpperCase().equals("HOOK")) {

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

                                    	list.add(new Fish(x,y));
                                        break;
                                    case 2:

                                    	list.add(new Food(x,y));
                                        break;
                                    case 3:

                                    	list.add(new Hook(x,y));
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

                        lineCompon = posi_XY[0].split(":");
                        objectNo = Integer.parseInt(lineCompon[1].trim());
                        if (lineCompon[0].trim().toUpperCase().equals("FISH")) {
                            objectType = 1;
                        } else if (lineCompon[0].trim().toUpperCase().equals("FOOD")) {
                            objectType = 2;
                        } else if (lineCompon[0].trim().toUpperCase().equals("HOOK")) {
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
                                    	list.add(new Fish(x,y));
                                        break;
                                    case 2:
                                    	list.add(new Food(x,y));
                                        break;
                                    case 3:
                                    	list.add(new Hook(x,y));
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

                if ( list.size() == 0) {

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

    SwimSimulation(PApplet a_PApplet) {


    	SimObject.setProcessing(a_PApplet);

    	
    	list = new ArrayList<SimObject>(); // new
    	
        if (initObjWithParaFile()) {
            return;
        }
        
        
        list = new ArrayList<SimObject>(); // new
        
        int times = 4;       

        for(int i = 0; i< times; i++)
        {
        	list.add(new Fish());
        	
        }
                
        times = 6;      

        for(int i = 0; i< times; i++)
        {
        	list.add(new Food());
        }
                
        times = 1;

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
    	

            SimObject.processing.background(0, 255, 255);

            for(int i =0; i < list.size(); i++)
            	for(int j = 0; j < list.size(); j++)
            		list.get(i).tryToInteract(list.get(j));

            for(int i =0; i < list.size(); i++)
            	list.get(i).update();
            
            
            for(int i=0;i <list.size();i++){
                if (list.get(i).shouldBeRemoved())
                {
                	list.remove(i);
                	i--;
                }
            
        }
    }
    public void handleClick(int mouseX, int mouseY) {
    	// hook[0].handleClick(mouseX, mouseY);
    
    	for(int i=0;i <list.size();i++)
    	{
            if (list.get(i) instanceof Hook)
            {
            	((Hook)list.get(i)).handleClick(mouseX, mouseY);
            }
    	}	
       
    }
}