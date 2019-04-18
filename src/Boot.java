import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Boot {

    public static void main(String[] args) {
        int ramBlocks;
        int max=0,choose;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество блоков в RAM: ");
        ramBlocks= scanner.nextInt();
        int  [] ram =  new int [ramBlocks];
        String[] planner = new String [ramBlocks];

        List <Operation> op = new ArrayList<>();
        for(int i =0;i<ramBlocks;i++)
        {
            System.out.println("Введите количество памяти для блока "+ i + " в Кb");
            ram[i]=scanner.nextInt();
            planner[i]="0";
        }
        while(true)
        {
            System.out.println("1-Создание нового процесса"+'\n'+"2-Удалить/Остановить процесс"+'\n'+
                   "3-Show ram status"+'\n'+"4-Exit Programm");
            choose = scanner.nextInt();

            switch (choose)
            {
                case 1:
                    int counter = 0;
                    Operation example = new Operation();
                    System.out.println("Name your operation: ");
                    String name = scanner.nextLine();
                    example.setName(name);

                    System.out.println("What size does your operation have?: ");
                    int size=scanner.nextInt();
                    example.setSize(size);

                    //Worse Fit Algorithm
                    for(int i=0;i<ramBlocks;i++)
                    {
                        if(planner[i].equals("0") && ram[i]>max)
                        {
                            max=ram[i];
                            counter=i;
                        }
                    }

                    if(ram[counter]>example.getSize()){
                        op.add(example);
                        planner[counter]=example.getName();
                    }
                    else System.out.println("Oh, your operation is too big!!");
                    break;
            }
        }
    }
}
