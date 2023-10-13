import java.util.*;

public class Main 
{
    public static void main(String[] args) {
        // ...
        run(new Scanner(System.in));
    }

    public static void run(Scanner scan) {
        UI.setScanner(scan);
        MainMenu.run();
        scan.close();
    }
}


class MainMenu {
    public static void run() {
        final int MENU_COUNT = 3; // Menu count를 3으로 변경
        
        String menuStr =
                "***** Main Menu ******\n" +
                "* 0.exit 1.ch2 2.ch3 *\n" +
                "**********************\n";

        int menuItem;
        do {
            System.out.print(menuStr); 
            menuItem = UI.selectMenu(MENU_COUNT); 

            switch(menuItem) {
                case 1:
                    Ch2.run();
                    break;
                case 2:
                    Ch3.run(); // Ch3 클래스의 run 메소드 호출
                    break;
                case 0:
                    break;
                default:
                    break;
            }
            
        } while(menuItem != 0);

        System.out.println("\nGood bye!!");
    }
}

class UI
{
    public static boolean echo_input = false; // 자동오류체크 시 true로 설정됨
    public static Scanner scan;

    public static void setScanner(Scanner s) { scan = s; }

    public static int getInt(String msg) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(msg);
            try {
                input = scan.nextInt();
                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println(e); // 예외 종류 출력
                System.out.println("-----");
                e.printStackTrace();   // 예외에 대한 상세한 정보 출력
                System.out.println("Input an INTEGER. Try again!!");
                scan.nextLine(); // 입력 스트림 버퍼의 잘못된 값 제거
            }
        }

        return input;
    }
    
    public static int getPosInt(String msg) { 
        int value;
        while (true) {
            value = getInt(msg);
            if (value >= 0) {
                break;
            }
            System.out.println("Input a positive INTEGER. Try again!!");
        }
        return value;
    }
    
    public static int getIndex(String prompt, int max) {
        while (true) {
            int value = getInt(prompt);

            if (value < 0) {
                System.out.println("Input a positive INTEGER. Try again!!\n");
            } else if (value >= max) {
                System.out.println(value + ": OUT of selection range(0 ~ " + (max - 1) + ") Try again!!\n");
            } else {
                return value;
            }
        }
    }
    
    public static int selectMenu(int menuCount) {
        return getIndex("menu item? ", menuCount); // "menu item?"만 출력
    }
    
    public static String getNext(String msg) {
        System.out.print(msg);  
        String input = scan.next(); 
        scan.nextLine(); // 버퍼 내의 줄 바꿈 문자 제거

        if (echo_input) {
            System.out.println(input);
        }

        return input;
    }

    public static String getNextLine(String msg) {
        System.out.print(msg);  // 메시지 출력
        String input = scan.nextLine(); // 사용자 입력 받기

        if (echo_input) { // 입력 내용을 출력할 경우
            System.out.println(input);
        }

        return input;
    }
}

class Ch3 
{
    public static void run() {
        final int MENU_COUNT = 4;

        String menuStr =
             "************* Ch3 Menu **************\n" +
             "* 0.Exit 1.array 2.exception 3.game *\n" +
             "*************************************\n";

        int menuItem;
        do {
            System.out.println();
            System.out.print(menuStr); 
            menuItem = UI.selectMenu(MENU_COUNT); 

            switch(menuItem) {
            case 1:
                array();
                break;
            case 2:
                exception();
                break;
            case 3:
                game();
                break;
            default:
                break;
            }
        } while(menuItem != 0);
    }

    public static void array() {
        double arr1[][] = { {0}, {1,2}, {3,4,5} };
        printArray(arr1);
        double arr2[][] = { {0,1,2,3}, {4,5,6}, {7,8}, {9} };
        printArray(arr2);
        
        var arr3 = inputArray();
        printArray(arr3);
        arr3 = inputArray();
        printArray(arr3);
    }

    public static void printArray(double arr[][]) {
        System.out.println("arr length: " + arr.length);
        for(int i=0; i < arr.length; i++) {
            System.out.print("arr[" + i + "] ");
            for(int j=0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(); // 빈줄 출력
    }
    
    public static double[][] inputArray() {
        int rows = UI.getPosInt("array rows? "); // 행의 개수를 입력받음
        double[][] arr = new double[rows][]; // 비정방형 배열 생성

        for (int i = 0; i < rows; i++) {
            arr[i] = new double[i + 1]; // 각 행의 길이는 그 행의 (인덱스+1) 값과 동일함

            System.out.print("input " + (i+1) + " doubles for row " + i + ": ");
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = UI.scan.nextDouble(); // 실수 값 입력 받기
            }
        }

        return arr; // 생성된 배열을 반환
    }


    static Random random = null; // 난수 발생기
    
    public static void exception() {
        var random = new Random(UI.getInt("seed for random number? "));
        int arr[] = null;

        while (true) {
            try {
                String str = UI.getNext("array[] size? ");
                int i = Integer.parseInt(str);
                arr = new int[i];
                break;
            } catch (NumberFormatException e) {
                System.out.println(e);
            } catch (NegativeArraySizeException e) {
                System.out.println(e);
            }
        }

        for (int i = 0; i < arr.length; ++i)
            arr[i] = random.nextInt(3);

        System.out.print("array[]: { ");
        for (var v : arr)
            System.out.print(v + " ");
        System.out.println("}");

        while (true) {
            try {
                int i = UI.getPosInt("array[] index? ");
                System.out.println("array[" + i + "] = " + arr[i]);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }

        int numerator = UI.getIndex("numerator   index? ", arr.length);
        while (true) {
            try {
                int denominator = UI.getIndex("denominator index? ", arr.length);
                System.out.println(arr[numerator] + " / " + arr[denominator] + " = " + (arr[numerator] / arr[denominator]));
                break;
            } catch (ArithmeticException e) {
                System.out.println(e);
            }
        }

        System.out.println("makeArray(): first");
        while (true) {
            try {
                arr = makeArray();
                break;
            } catch (OutOfMemoryError e) {
                System.out.println("java.lang.OutOfMemoryError: " + e.getMessage());
            }
        }

        System.out.println("makeArray(): second");
        while (true) {
            try {
                arr = makeArray();
                System.out.println("array length = " + arr.length);
                break;
            } catch (NullPointerException e) {
                System.out.println("NullPointerException");
            }
        }
    }


    
    // tag 0: OutOfMemoryError, 1: return null pointer, 2: return normal array
    
    public static int[] makeArray() { 
        int tag = UI.getPosInt("makeArray tag[0,1,2]? ");
    	return (tag == 0)? new int[0x7fffffff]: (tag == 1)? null: new int[10]; 
    }
    
    public static void game() {
        final int USER = 0;
        final int COMPUTER = 1;
        String MJBarray[] = { "m", "j", "b" };
        System.out.println("Start the MUK-JJI-BBA game.");
        random = new Random(UI.getInt("seed for random number? "));
        int priority = USER; 
        String priStr[] = { "USER", "COMPUTER"};

        while(true) {
            System.out.println();
            System.out.println(priStr[priority] + " has the higher priority.");
            String user = UI.getNext("m(muk), j(jji), b(bba) or stop? ");

            if (user.equals("stop")) {
                break;
            }
            if (!user.equals("m") && !user.equals("j") && !user.equals("b")) {
                System.out.println("Select one among m, j, b.");
                continue;
            }

            String computer = MJBarray[random.nextInt(MJBarray.length)];

            if (user.equals(computer)) {
                System.out.print("User = " + user + ", Computer = " + computer + ", ");
                if (priority == USER) {
                    System.out.println("USER WINs.");
                } else {
                    System.out.println("COMPUTER WINs.");
                }
            } else {
                System.out.println("User = " + user + ", Computer = " + computer + ", SAME.");
                if ((user.equals("m") && computer.equals("j")) || 
                    (user.equals("j") && computer.equals("b")) || 
                    (user.equals("b") && computer.equals("m"))) {
                    priority = USER;
                } else {
                    priority = COMPUTER;
                }
            }
        }
    }


}


class Ch2 
{
	public static void run() {
	    final int MENU_COUNT = 6;

	    String menuStr =
	         "************* Ch2 Menu ***********\n" +
	         "* 0.exit 1.output 2.readToken    *\n" +
	         "* 3.readLine 4.operator 5.switch *\n" +
	         "**********************************\n";

	    int menuItem;
	    do {
	        System.out.println();
	        System.out.print(menuStr); 
	        menuItem = UI.selectMenu(MENU_COUNT); 

	        switch(menuItem) {
	        case 1:
	            output();
	            break;
	        case 2:
	            readToken(); 
	            break;
	        case 3:
	            readLine();
	            break;
	        case 4:  // operator를 선택한 경우
	            operator();
	            break;
            case 5:
                switchCase();
                break;
	        default:
	            break;
	    }
	    } while(menuItem != 0);
	}

    public static void output() {
        String toolName = "JDK";
        double version = 1.8;
        String released = "is released.";

        System.out.println(toolName + version + released);
        System.out.println(toolName + " " + version + " " + released);

        int i1 = 1, i2 = 2, i3 = 3; 
        
        System.out.println(i1 + i2 + i3);
        System.out.println("" + i1 + i2 + i3);
        System.out.println((i1 + i2 + i3) + " " + i1 + i2 + i3);

        boolean b = true;
        double d = 1.2;

        System.out.println(b + " " + !b + " " + d);
    }
    
    public static void readToken() {

        String name;    // 이름
        int id;         // Identifier
        double weight;  // 체중
        boolean married; // 결혼여부
        String address; // 주소

        System.out.print("person information(name id weight married :address:): ");

        name = UI.scan.next();
        id = UI.scan.nextInt();
        weight = UI.scan.nextDouble();
        married = UI.scan.nextBoolean();

        while ((address = UI.scan.findInLine(":.*:")) == null)
        	UI.scan.nextLine();  
        address = address.substring(1, address.length()-1); 
        System.out.println(name + " " + id + " " + weight + " " + married + " :" + address + ":");
    }

    public static void readLine() {
        String name = UI.getNext("name? "); 
        System.out.println("name: " + name);
        
        int id = UI.getInt("id? ");         
        System.out.println("id: " + id);
        
        String address = UI.getNextLine("address? ");
        System.out.println("address :" + address + ":");
    }
    
    public static void printBinStr(int v) {
    	String s = Integer.toBinaryString(v);
        for (int i=0; i < (32-s.length()); ++i)
            System.out.print('0');
        System.out.println(s);
    }
    
    public static void operator() {
        int b = 0b11111111_00000000_11111111_11111111;
        printBinStr(b);

        // TODO: 변수 b를 왼쪽으로 4비트 이동시킨 값을 출력하라.
        b = b << 4;
        printBinStr(b);

        System.out.println();
        b = 0b11111111_00000000_00000000_11111111;
        printBinStr(b);

        // TODO: 변수 b를 4비트 산술적 오른쪽 시프트를 한 값을 출력하라.
        b = b >> 4;
        printBinStr(b);

        // TODO: 변수 b를 4비트 논리적 오른쪽 시프트를 한 값을 출력하라.
        b = 0b11111111_00000000_00000000_11111111; // 다시 초기화
        b = b >>> 4;
        printBinStr(b);
    }

    public static void switchCase() {
        String menuStr =
            "********* Switch Menu *********\n" +
            "* 0.exit 1.output 2.readToken *\n" +
            "* 3.readLine 4.operator       *\n" +
            "*******************************\n";

        while (true) {
            String menu = UI.getNext("\n"+menuStr+"menu item string? ");
            
            switch(menu) {
                case "exit":
                    return;
                case "output":
                    output();
                    break;
                case "readToken":
                    readToken();
                    break;
                case "readLine":
                    readLine();
                    break;
                case "operator":
                    operator();
                    break;
                default:
                    System.out.println("Invalid menu item. Try again!");
                    break;
            }
        }
    }
}    

