import java.util.*;
public class Boot {
    public static int ramSize = 0;
    public static int[] RamCells;
    public static List<Operation> processes = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter a size of RAM: ");
        ramSize = scanner.nextInt();
        RamCells = new int[ramSize];
        for(int  i= 0;i<ramSize;i++)
        {
            RamCells[i]=0;
        }
        while (true)
        {
            int choice = 0;
            boolean dobbieIsFree = false;
            System.out.println("1-Create new process");
            System.out.println("2-Kill some process");
            System.out.println("3-Show RAM status");
            System.out.println("4-Exit programm");
            choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    create();
                    break;
                case 2:
                    kill();
                    break;
                case 3:
                    show();

                    break;
                case 4:
                    dobbieIsFree = true;
            }
            if (dobbieIsFree==true)
            {
                break;
            }
        }
    }
    static void create()
    {
        System.out.println("Input a name of process:");
        String  name = scanner.next();
        System.out.println("Its size: ");
        int size = scanner.nextInt();
        if(size>ramSize)
        {
            System.out.println("Oh, your operation is too big");
            return;
        }
        System.out.println("And its unique number");
        int unique = scanner.nextInt();
        //check if it is unique
        for(int i =0;i<processes.size();i++)
        {
            if(processes.get(i).getUnique()==unique)
            {
                System.out.println("Operation with this unique number is already exists");
                return;
            }
        }
        //check if it is enough space
        int maxSpace = 0,start = 0,spacer=0,finalStart=0;
        for(int i =0;i<ramSize;i++){
            if(RamCells[i]==0)
            {
                start = i;

                for(int j  = i;j<ramSize;j++)
                {
                    i=start+spacer;
                    if(RamCells[j]!=0)
                    {
                        break;
                    }
                    else
                        spacer++;
                }
            }
            if(spacer>maxSpace)
            {
                maxSpace=spacer;
                finalStart=start;
                spacer=0;
            }
        }

        if(maxSpace>size)
        {

            System.out.println("max length of empty space is:"+maxSpace+"\n");
            System.out.println("Start is:"+finalStart+'\n');
            Operation operation = new Operation();
            operation.setName(name);
            operation.setSize(size);
            operation.setUnique(unique);
            processes.add(operation);
            for(int i=finalStart;i<finalStart+size;i++)
            {
                RamCells[i]=unique;
            }
        }
        else{
            System.out.println("Sorry, not enough space ");
        }


    }
    static void show()
    {
        System.out.println("*****************************************");
        for(int i = 0;i<ramSize;i++)
        {
            System.out.print(RamCells[i]+"  ");
        }
        System.out.println("\n");
        System.out.println("Unique number|Name         |Size");
        for(int i = 0;i<processes.size();i++)
        {
            System.out.println("      "+processes.get(i).getUnique()+"      |    "+processes.get(i).getName()+"      |  "+processes.get(i).getSize());
        }
        System.out.println("*****************************************");
        System.out.println("\n");
        System.out.println("\n");

    }
    static  void kill()
    {
        System.out.println("Unique number|Name         |Size");
        for(int i = 0;i<processes.size();i++)
        {
            System.out.println("      "+processes.get(i).getUnique()+"      |    "+processes.get(i).getName()+"      |  "+processes.get(i).getSize());
        }
        System.out.println("Input the unique number of operation:");
        int victim = scanner.nextInt();
        for(int i =0;i<processes.size();i++)
        {
            if(processes.get(i).getUnique()==victim)
            {
                processes.remove(i);
            }
        }
        for(int i =0;i<ramSize;i++)
        {
            if(RamCells[i]==victim)
            {
                RamCells[i]=0;
            }
        }
    }



}
