import java.util.*;

//===============================================================================
// main()
//===============================================================================
public class Main 
{
    public static void main(String[] args) {
        //--------------------------------
        // AutoCheck(chk, trace)
         //chk: 1(자동 오류 체크), 0(키보드에서 직접 입력하여 프로그램 실행)
        // trace: true(오류발생한 곳 출력), false(단순히 O, X만 표시)
        //--------------------------------
      //int chk = 1; if (chk != 0) new AutoCheck(chk, true).run(); else

        run(new Scanner(System.in));
    }

    public static void run(Scanner scan) {
        // UI 클래스의 setScanner() 함수를 호출함; setScanner()가 static 함수라 이렇게 호출 가능함
        UI.setScanner(scan); // UI 클래스 내의 static 함수 호출
        MainMenu.run(); // MainMenu 클래스 내의 static 함수 호출방법: 클래스이름.함수이름();
        
        scan.close();
        
    }
}

//===============================================================================
// Main Menu
//===============================================================================
class MainMenu
{
   public static final int MENU_COUNT = 4;
   
    public static void run() {
       String menuStr =
                "******* Main Menu ********\n" +
                "* 0.exit 1.PersonManager *\n" +
                "* 2.ch2 3.ch3            *\n" +
                "**************************\n";
        
        int menuItem;
        while(true) {
        menuItem = UI.selectMenu("\n"+menuStr, MENU_COUNT);
        
        if(menuItem == 0) {
       //System.out.println("menuItem: " + menuItem);
        System.out.println("\nGood bye!!");
        break;
       }
        else {
           switch(menuItem) {
           case 1:
              new MultiManager().run();
              break;
           case 2:
              Ch2.run();
              break;
           case 3:
              Ch3.run();
              break;
           default:
        System.out.println("menuItem: " + menuItem);
           }
        }
    }
  }
} // class MainMenu

//===============================================================================
//class Person: ch4_1
//===============================================================================
class Person 
{
 private String  name;    // 이름
 private int     id;      // Identifier
 private double  weight;  // 체중
 private boolean married; // 결혼여부
 private String  address; // 주소
 
 String passwd;
 

 
 // 생성자 함수들
 public Person(String name, int id, double weight, boolean married, String address) { 
    set(name,"", id, weight, married, address);
    System.out.print("Person(): ");  printMembers();  System.out.println();
 }
 
 public Person(String name) {
     this(name, 0, 0.0, false, "");
 }
 
 public Person(Scanner sc) { inputMembers(sc); }
 
 
 public void set(String name, String passwd, int id, double weight, boolean married, String address) {
    this.name = name;
    this.passwd = passwd;
    this.id = id;
    this.weight = weight;
    this.married = married;
    this.address = address;
 }
 
  
 public void println() { 
     print(); System.out.println(); 
 }
  
 public void println(String msg) { 
      System.out.print(msg); print(); System.out.println(); 
 }
 
 // assign() 함수
 public void assign(Person p) {
     this.name = p.name;
     this.passwd = p.passwd;
     this.id = p.id;
     this.weight = p.weight;
     this.married = p.married;
     this.address = p.address;
 }
 

 // Getter: getXXX() 관련 함수들
 public String getName() {
    return name;
 }
 
public int getId() {
    return id;
 }

public double getWeight() {
   return weight; 
 }

public boolean getMarried() {
    return married;
 } 

public String getAddress() {
    return address;
 }
  
 // Setter: overloading: set() 함수 중복
public void set(String name) {
    this.name = name;
 }

public void set(int id) {
    this.id = id;
 }

public void set(double weight) {
    this.weight = weight;
 }

public void set(boolean married) {
    this.married = married;
 }

public void setAddress(String address) {
    this.address = address;
 }

 // Candidates for virtual functions and overriding 
 // print(), clone(), whatAreYouDoing(), equals(), input() 함수
 void print() { 
    printMembers();
 }

public Person clone() {
    System.out.println("Person::clone()");
    Person clonedPerson = new Person(name, id, weight, married, address);
    return clonedPerson;
 }

public void whatAreYouDoing() {
   System.out.println(name +" is taking a rest.");
 }

public boolean equals(Object obj) {
   if(this == obj) {
      return true;
   }
   
   if(obj == null || getClass() != obj.getClass()) {
      return false;
   }
   
   Person other = (Person) obj;
   
   return this.id == other.id && this.name.equals(other.name);
 }

 public void input(Scanner sc) { 
    inputMembers(sc);
 }
 
 private void inputMembers(Scanner sc) {
       name = sc.next();
       passwd = "";
       id = sc.nextInt();
       weight = sc.nextDouble();
       married = sc.nextBoolean();

       while ((address = sc.findInLine(":.*:")) == null)
       sc.nextLine();
       //address = sc.nextLine();
       
       address = address.substring(1, address.length() - 1);
 }
 
 String getPasswd() {
    return passwd;
 }
 
 public void setPasswd(String passwd) {
   this.passwd = passwd; 
 }
 
 private void printMembers() {
    System.out.print(name+" "+id+" "+weight+" "+married+" :"+address+":");
 }
}

//===============================================================================
// User Interface
//===============================================================================
class UI
{
    public static boolean echo_input = false; // 자동오류체크 시 true로 설정됨
    public static Scanner scan;

    public static void setScanner(Scanner s) { scan = s; }

    // 입력을 받기 위해 static Scanner scan 멤버를 활용하라. 즉, scan.함수() 형식으로 호출
    // 정수 값을 입력 받아 리턴함 (음수, 0, 양수)
    public static int getInt(String msg) { 

        // TODO: msg를 화면에 출력한 후 정수 값을 입력 받아 지역 변수 value에 저장함 (변수 선언할 것)
       //       입력 시 이 클래스의 scan 멤버 변수를 활용하라. 
        //       (이 변수는 setScanner(Scanner s)에 의해 이미 초기화 되었음)
       int value = 0;
       while(true) {
          try {
             System.out.print(msg);
             value = scan.nextInt();
       
             scan.nextLine();

             if (echo_input) System.out.println(value);
             break;
         // 자동오류체크 시 입력 값을 출력함
        // 위 문장은 자동오류체크 시에 사용되는 문장임; 일반적으로 키보드로부터 입력받을 경우 
        // 화면에 자동 echo되지만, 자동오류체크 시에는 입력파일에서 입력받은 값이 자동 echo 되지  
        // 않으므로 명시적으로 출력 버퍼에 출력(echo) 해 주어야 한다.

        // (지시가 있을 때 구현할 것) 입력 버퍼에 남아 있는 '\n'를 제거하지 않으면 다음번 getLine()에서 
        // '\n'만 빈 줄이 입력될 수 있으므로 입력 버퍼에 남아 있는 '\n'를 사전에 제거함
       } catch(InputMismatchException e) {
          System.out.println(e); // 예외 종류 출력
            System.out.println("-----"); 
            e.printStackTrace();
             System.out.println("Input an INTEGER. Try again!!");
             scan.nextLine();
       }
     }
        return value; // TODO: 입력 받은 정수 value를 리턴할 것 
    }

    // 0 또는 양의 정수 값을 입력 받아 리턴함
    public static int getPosInt(String msg) { 
       int value;
       while(true) {
          value = getInt(msg);
          if(value >= 0) {
             //System.out.println("menu item: " + value);      
             break;
          } else {
             System.out.println("Input a positive INTEGER. Try again!!");
          }
       }
        return value;
    }
    
    // [0, (size-1)] 사이의 인덱스 값을 리턴함
    // 존재하지 않는 메뉴항목을 선택한 경우 에러 출력
    public static int getIndex(String msg, int size) { 
 
       int index;
       while (true) {
          index = getPosInt(msg);
          if(index >= 0 && index < size) {
             break;
          }
          else {
             System.out.println(index + ": OUT of selection range(0 ~ " + (size - 1) + ") Try again!!");
          }
       }
        return index;
    }

    // 사용자에게 메뉴("\n"+menuStr+"menu item? ")를 보여주고 
    // 위 getIndex(String msg, int size)를 호출해서
    // 사용자가 선택한 메뉴항목의 인덱스(0 ~ (menuCount-1))를 리턴함
    // menuCount: 메뉴항목의 개수임
    public static int selectMenu(String menuStr, int menuCount) { 
       
       int menuItem;
       while(true) {
          menuItem = getIndex("\n" + menuStr + "menu item? ", menuCount);
          if(menuItem >= 0 && menuItem < menuCount) {
             break;
          }
          else {
             System.out.println(menuItem + ": OUT of selection range(0 ~ " + (menuCount - 1) + ")Try again!!");
          }
       }
       return menuItem; 
       
    }

    // 중요: 위 각 함수 구현 시
    // selectMenu() -> getIndex() -> getPosInt() -> getInt() 를 호출해서 구현하여야 한다.

    // 위 getInt()를 참고하여 msg를 화면에 출력한 후 문자열 단어 하나를 입력 받아 리턴함
       
    public static String getNext(String msg) { 
        // TODO: msg를 화면에 출력한 후 하나의 토큰(단어)을 입력 받아 변수 token에 저장함
       System.out.print(msg);
       String token = scan.next();
       
       scan.nextLine();
       
        if (echo_input) System.out.println(token); // 자동오류체크 시 입력 값을 출력함
        return token;  // TODO: 입력 받은 한 단어를 리턴할 것
    }

    // msg를 화면에 출력한 후 하나의 행 전체를 입력 받아 리턴함
    public static String getNextLine(String msg) { 
        // TODO: msg를 화면에 출력한 후 한 행 전체를 입력 받아 변수 line에 저장함
       System.out.print(msg);
       
       String line = scan.nextLine();
       
        if (echo_input) System.out.println(line); // 자동체크 시 출력됨
        return line;  // TODO: 입력 받은 한 행 전체를 리턴할 것
    }
}

//===============================================================================
//CurrentUser class: ch4_1
//===============================================================================
class CurrentUser
{
 Person user;

 CurrentUser(Person user) { 
     this.user = user; 
 }

 public void run() {
    String menuStr =
               "++++++++++++++++++++++ Current User Menu ++++++++++++++++++++++++\n" +
               "+ 0.logout 1.display 2.getter 3.setter 4.copy 5.whatAreYouDoing +\n" +
               "+ 6.isSame 7.update 8.chPasswd(4_2)                             +\n" +
               "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
     final int MENU_COUNT = 9; // 상수 정의
     while (true) {
         int menuItem = UI.selectMenu(menuStr, MENU_COUNT);
         switch(menuItem) {
         case 1: display();         break;
         case 2: getter();          break;
         case 3: setter();          break;
         case 4: copy();            break;
         case 5: whatAreYouDoing(); break;
         case 6: equals();          break;
         case 7: update();          break;
         case 8: chPasswd();      break;
         case 0:                    return;
         }
     }
 }
 void display() { 
     user.println(); 
 } // Menu item 1

 void getter() { // Menu item 2
    System.out.println("name:" + user.getName() + ", id:" + user.getId() + ", weight:" +
             user.getWeight() + ", married:" + user.getMarried() +
             ", address:" + user.getAddress());
 }
 
 void setter() { // Menu item 3
    var p = new Person("p");
    
    p.set(p.getName());
     p.set(user.getId());
     p.set(user.getWeight());
     p.set(user.getMarried());
     p.setAddress(user.getAddress());
     p.println("p.set(): ");
 }
 
 void copy() { // Menu item 4
    user.println("user: ");
     var p = user.clone();
     p.println("p: ");
 }
 
 void whatAreYouDoing() {  // Menu item 5
    user.whatAreYouDoing();
 }
 
 void equals() { // Menu item 6
    user.println("user: ");
     var p = new Person("user"); 
     p.set(1);
     p.println("p: ");
     System.out.println("p.equals(user): " + p.equals(user));
     
     p.assign(user);
     p.println("p.assign(user): ");
     System.out.println("p.equals(user): " + p.equals(user));
 }
 
 void update() { // Menu item 7
    System.out.println("input person information:");
     user.input(UI.scan); // user 100 65 true :426 hakdong-ro, Gangnam-gu, Seoul:
     if (UI.echo_input) user.println(); // 자동오류체크시 출력됨
     display();
 }
 
 void chPasswd() { // Menu item 8
     String passwd = UI.getNext("new password: ");
     user.setPasswd(passwd);
     System.out.println("password changed");
 }
} // CurrentUser class: ch4_1

class VectorPerson
{
    static final int DEFAULT_SIZE = 10;

    private Person[] persons; // Person 객체 참조들의 배열, 즉 배열에 저장된 값이 Person 객체의 주소이다.
    private int count;        // persons 배열에 현재 삽입된 객체의 개수

    public VectorPerson() { this(DEFAULT_SIZE); }

    public VectorPerson(int capacity) {
        count = 0; // persons 배열에 현재 삽입된 객체의 개수는 0
        System.out.println("VectorPerson::VectorPerson("+capacity+")");
        persons = new Person[capacity]; // 객체 참조 배열 할당
    }
    
    // persons[index]의 값을 반환
    public Person get(int index) { 
          if(index <0 || index >= count) {
             return null;
          }
       
       return persons[index]; 
       }
     
    // 할당 받은 persons 배열의 전체 길이를 반환함 (count가 아님)
    public int capacity() { return persons.length; }
     
    // persons 배열에 현재 삽입된 객체의 개수를 0으로 설정
    public void clear() { 
       for(int i =0; i<count; i++) {
          persons[i] = null;
       }
       count = 0; 
    }
     
    // 현재 삽입된 객체 참조가 하나도 없으면 true, 있으면 false를 반환한다.
    // if 문장을 사용하지 말고 한 문장(return 비교연산자)으로 완셩할 것
    public boolean isEmpty() { return count == 0; }
     
    // 현재 삽입된 객체의 개수를 반환
    public int size() { return count; }

    // index 위치의 객체 p를 삭제한다. 즉, index+1부터 끝까지 객체들을 한칸씩 왼쪽으로 밀어 주어야 한다.
    // 자바에는 객체를 삭제하는 delete 명령어가 없다. 따라서 객체를 별도로 삭제할 필요는 없고 무시하라.
    void remove(int index) {
       if(index >= 0 && index < count) {
          for(int i = index; i < count -1; i++) {
             persons[i] = persons[i+1];
          }
          persons[count - 1] = null;
          count--;
       }
    }
    
    // persons 배열에 마지막 삽입된 원소 뒤에 새로운 원소 p를 삽입하고 현재 삽입된 객체 개수 증가
    // persons[]의 배열 크기가 작으면 extend_capacity()를 호출하여 먼저 배열을 확장한다.
    public void add(Person p) {
         if (count >= persons.length)
             extend_capacity();
         persons[count++] = p;
    }
    
    // 먼저 index부터 끝까지 객체들을 한칸씩 뒤로 밀어 준 후 index 위치에 객체 p를 삽입한다. 
    // persons[]의 배열 크기가 작으면 extend_capacity()를 호출하여 먼저 배열을 확장한다.
    public void add(int index, Person p) {
       if(index < 0 || index > count) {
          return;
       }
       if(count >= persons.length)
          extend_capacity();
       
       for(int i = count; i>index; i--) {
          persons[i] = persons[i - 1];
       }
       
       persons[index] = p;
       count++;
    }
    
    // persons[]의 배열 크기를 두배로 확장한다.
    // 기존 persons 변수를 다른 배열 변수에 임시로 저장한 후 
    // 현재의 두배 크기의 배열을 새로 할당 받아 persons에 저장한다.
    // 임시 변수에 있던 기존 값들을 모두 persons[]에 복사한다.
    public void extend_capacity() {
       int newCapacity = persons.length * 2;
        Person[] newPersons = new Person[newCapacity];
        for (int i = 0; i < count; i++) {
            newPersons[i] = persons[i];
        }
        persons = newPersons;
        System.out.println("VectorPerson: capacity extended to " + newCapacity);
        // 별도의 지시가 있을 때가지 구현하지 않아도 됨
    }
}   // VectorPerson class: ch4_2

class Factory
{
    public void printInputNotice(String preMsg, String postMsg) {
        System.out.println("input"+preMsg+" [person information]"+postMsg+":");
    }
    public Person inputPerson(Scanner sc) { 
       // user 100 65 true :426 hakdong-ro, Gangnam-gu, Seoul:
        var p = new Person(sc); // 위 행처럼 입력된 사람 정보를 입력 받음
        if (UI.echo_input) p.println(); // 자동오류체크시 출력됨
        return p; 
    }
}   // Factory class: ch4_2

class PersonManager
{
    static int cpCount = 0;
    private VectorPerson pVector;
    private Factory factory;
    private Person array[];

    public PersonManager(Person array[], Factory factory) {
        System.out.println("PersonManager(array[])");
        pVector = new VectorPerson();
        this.factory = factory;
        this.array = array;
        addArray();
        display();
    }

    public void run() {
        String menuStr =
            "=========== Person Management Menu ==========\n" +
            "= 0.exit 1.display 2.clear 3.reset 4.remove =\n" +
            "= 5.copy 6.append 7.insert 8.login          =\n" +
            "=============================================\n";
        final int MENU_COUNT = 9; // 상수 정의
        while (true) {
            int menuItem = UI.selectMenu(menuStr, MENU_COUNT);
            switch(menuItem) {
            case 1: display();         break;
            case 2: clear();           break;
            case 3: reset();           break;
            case 4: remove();          break;
            case 5: copy();            break;
            case 6: append();          break;
            case 7: insert();          break;
            case 8: login();           break;
            case 0:                    return;
            }
        }
    }
    public void display() { // Menu item 1
       int count = pVector.size();
        System.out.println("display(): count " + count);
        for (int i = 0; i < count; ++i) {
            System.out.print("[" + i + "] ");
            pVector.get(i).println();
        }
        System.out.println("empty():" + pVector.isEmpty() + ", size():" + pVector.size()
             + ", capacity():" + pVector.capacity());
    }
    public void clear() {  // Menu item 2
       pVector.clear();
        display();
    }
    public void reset() { // Menu item 3
       pVector.clear();
        addArray();
        display();
    }
    public void remove() { // Menu item 4
       if (pVector.size() == 0) {
            System.out.println("no entry to remove");
            return;
        }
        int index = UI.getIndex("index to delete? ", pVector.size());
        pVector.remove(index);
        display();
    }
    public void copy() { // Menu item 5
       cpCount++;
        for (int i = 0, size = pVector.size(); i < size; ++i) {
            Person p = pVector.get(i).clone();
            String name = p.getName();
            for (int j = 0; j < cpCount; ++j)
                name = name.charAt(0)+name;
            p.set(name);
            p.set(p.getId() + 20 * cpCount);
            p.set(p.getWeight() + cpCount);
            if (cpCount % 2 == 1)
                p.set(!p.getMarried());
            pVector.add(p);
        }
        display();
    }
    // 아래 함수는 사용자로부터 새로 추가할 Person 객체의 수를 입력 받고 for문을 이용하여
    // 그 개수만큼의 Person 객체를 생성하고 인적정보를 입력받은 후 (factory.inputPerson()을 통해)
    // VectorPerson pVector의 맨 끝에 추가한다.
    /* append() 실행 시 아래 항목들을 복사해서 순서적으로 입력하면 편하게 인적정보를 입력할 수 있음
    3
    HongGilDong 0 71.5 false :Gwangju Nam-gu Bongseon-dong 21:
    LeeMongRyong 1 65 true :Jong-ro 1-gil, Jongno-gu, Seoul:
    LeeSoonShin 2 80 true :1001, Jungang-daero, Yeonje-gu, Busan:
    */
    public void append() { // Menu item 6
       int count = UI.getPosInt("number of persons to append? ");
        factory.printInputNotice(" "+Integer.toString(count), " to append");
        for (int i = 0; i < count; ++i) {
            Person p = factory.inputPerson(UI.scan); // 한 사람의 정보를 입력 받음
            if (p != null) pVector.add(p);
        }
        display();
    }
    public void insert() { // Menu item 7
       int index = 0;
        if (pVector.size() > 0) {
            index = UI.getIndex("index to insert in front? ", pVector.size()+1);
            if (index < 0) return;
        }
        factory.printInputNotice("", " to insert");
        Person p = factory.inputPerson(UI.scan);
        if (p == null) return;
        pVector.add(index, p);
        display();
    }
    // 사용자로부터 VectorPerson pVector에 저장된 사람들 중에서 로그인할 사람의 이름(name)과 비번을 입력받고
    // 해당 비번이 맞으면 CurrentUser의 객체를 생성하고 이 객체의 run() 멤버 함수를 호출한다.
    // 초기 비번은 설정되어 있지 않기에 그냥 엔터치면 된다.
    public void login() {  // Menu item 8
       String name = UI.getNext("user name: ");
        Person p = findByName(name);
        if (p == null) return;
        String passwd = UI.getNextLine("password: ");
        //passwd.strip();
        if (passwd.equals(p.getPasswd()))
            new CurrentUser(p).run();
        else
            System.out.println("WRONG password!!");
    }
    
 // pVector에 삽입되어 있는 Person 객체들 중 사용자가 입력한 이름 name과 
    // 동일한 이름을 가진 객체를 찾아 리턴한다.
    private Person findByName(String name) {
        int i, count = pVector.size();
        for (i = 0; i < count; ++i)
            if (name.equals(pVector.get(i).getName())) 
                return pVector.get(i);
        System.out.println(name + ": NOT found");
        return null;
    }
    
    private void addArray() {
        for (Person p : array)
            pVector.add(p.clone()); // 배열의 각 원소를 복사한 후 pVector에  삽입함
    }
}   // ch4_2: PersonManager class

//===============================================================================
//class MultiManager: ch4_1
//===============================================================================
class MultiManager
{
    private Person persons[] = {
        new Person("p0", 10, 70.0, false, "Gwangju Nam-gu Bongseon-dong 21"),
        new Person("p1", 11, 61.1, true,  "Jong-ro 1-gil, Jongno-gu, Seoul"),
        new Person("p2", 12, 52.2, false, "1001, Jungang-daero, Yeonje-gu, Busan"),
        new Person("p3", 13, 83.3, true,  "100 Dunsan-ro Seo-gu Daejeon"),
        new Person("p4", 14, 64.4, false, "88 Gongpyeong-ro, Jung-gu, Daegu"),
    };
    // new를 이용해 동적으로 할당할 경우 소멸자 함수를 만들어 거기서 delete 해 주어야 함

    private Person allPersons[] = {
        persons[0], persons[1], persons[2], persons[3], persons[4],
    };

    public void run() {
        System.out.println("PersonManager::run() starts");
        var pm = new PersonManager(allPersons, new Factory());
        pm.run();
        System.out.println("PersonManager::run() returned");
    }
} // class MultiManager: ch4_1

//===============================================================================
//class Ch3
//===============================================================================
class Ch3 
{
   public static final int MENU_COUNT = 4;
   
  public static void run() {
      String menuStr =
           "************* Ch3 Menu **************\n" +
           "* 0.Exit 1.array 2.exception 3.game *\n" +
           "*************************************\n";

      // TODO: Ch2::run() 함수를 참고하여 while문과 switch문을 작성하라.
      //       switch에서는 아래의 상응하는 함수를 호출하고, MENU_COUNT도 적절히 수정하라.
      int menuItem;
      while(true) {
         menuItem = UI.selectMenu("\n"+menuStr, MENU_COUNT);
         switch(menuItem) {
         case 0:
            return;
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
          System.out.println("menuItem: " + menuItem);
          break;
         }
      
      }
  }
 

  public static void array() {
      double arr1[][] = { {0}, {1,2}, {3,4,5} };
      printArray(arr1);
      double arr2[][] = { {0,1,2,3}, {4,5,6}, {7,8}, {9} };
      printArray(arr2);
     // System.out.println("arr length: ");
      
     var arr3 = inputArray();
     printArray(arr3);
     arr3 = inputArray();
     printArray(arr3);
     
      
  }
  public static void printArray(double arr[][]) {
     System.out.println("arr length: "+arr.length);
     for(int i=0; i<arr.length; i++) {
        System.out.print("arr["+i+"] ");
        for(int j=0; j<arr[i].length;j++) {
           System.out.print(arr[i][j]+" ");
        }
        System.out.println();
     }
     System.out.println();
  }
  
  public static double[][] inputArray() {
     int row;
     System.out.print("array rows? ");
     row = UI.scan.nextInt();
     double arr[][] = new double[row][];
     for(int i=0; i<row; i++) {
        System.out.print("input "+(i+1)+" doubles for row "+i+": ");
        arr[i] = new double[i+1];
        for(int j=0; j<=i; j++) 
           arr[i][j] = UI.scan.nextDouble();
    }
     System.out.println();
     return arr;
  }
  
  static Random random = null; // 난수 발생기
  
  public static void exception() {

     var random = new Random(UI.getInt("seed for random number? "));// 난수 발생기
     String str;
     int i;
     int arr[];
     while(true) {
        try {
           str = UI.getNext("array[] size? ");
            i = Integer.parseInt(str);   // 문자열 숫자를 정수로 변환: "123" -> 123
            arr= new int[i];
           break;
        }
        catch(NumberFormatException e) {
           System.out.println(e);
           continue;
        }
        catch(NegativeArraySizeException e) {
           System.out.println(e);
           continue;
        }
     }


     for (i = 0; i < arr.length; ++i) // arr[] 전체를 난수 값으로 초기화
          arr[i] = random.nextInt(3);  // [0,1,2] 범위의 난수 발생
     System.out.print("array[]: { ");
     for (var v : arr)                // 배열 전체 출력
          System.out.print(v+" ");     // 각각의 v=arr[i] 원소 값을 출력함  
     System.out.println("}");
     while(true) {
        try {
           i = UI.getPosInt("array[] index? ");
           System.out.println("array["+i+"] = "+arr[i]);
           break;
        }
        catch(ArrayIndexOutOfBoundsException e) {
           System.out.println(e);
           continue;
        }
     }
     
     int numerator   = UI.getIndex("numerator   index? ", arr.length); // 분자 index
     while(true) {
        try {
           int denominator = UI.getIndex("denominator index? ", arr.length); // 분모 index
              System.out.println(arr[numerator]+" / "+arr[denominator]+" = "
                    +(arr[numerator] / arr[denominator]));
              break;
        }
        catch(ArithmeticException e) {
           System.out.println(e);
           continue;
        }
     }
      System.out.println("makeArray(): first");
     while(true) {
        try {
           arr = makeArray();
           break;
        }
        catch(OutOfMemoryError e) {
           System.out.println(e);
           continue;
        }
        catch(NullPointerException e) {
           System.out.println("NullPointerException");
           continue;
        }
     }
      System.out.println("makeArray(): second");
     while(true) {
        try {
           arr = makeArray();
           System.out.println("array length = "+arr.length);
           break;
        }
        catch(OutOfMemoryError e) {
           System.out.println(e);
           continue;
        }
        catch(NullPointerException e) {
           System.out.println("NullPointerException");
           continue;
        }
     }

     }

  // tag 0: OutOfMemoryError, 1: return null pointer, 2: return normal array
  public static int[] makeArray() { 
      int tag = UI.getPosInt("makeArray tag[0,1,2]? ");
     return (tag == 0)? new int[0x7fffffff]: (tag == 1)? null: new int[10]; 
  }
  
  public static void game() {
      final int USER = 0;     // 상수 정의
      final int COMPUTER = 1;
      String MJBarray[] = { "m", "j", "b" }; // 묵(m) 찌(j) 빠(b) 문자열을 가진 배열
      System.out.println("Start the MUK-JJI-BBA game.");
      // 난수 발생기
      random = new Random(UI.getInt("seed for random number? "));
      // 누가 우선권을 가졌는지 저장하고 있음, USER:사용자 우선권, COMPUTER:computer 우선권
      int priority = USER; 
      String priStr[] = { "USER", "COMPUTER"}; // 우선권을 화면에 출력할 때 사용할 문자열

      while(true) {
         System.out.println();
         System.out.println(priStr[priority] + " has the higher priority.");//를 출력
        String user = UI.getNext("m(muk), j(jji), b(bba) or stop? ");//를 출력하고
         // 사용자가 입력한 묵찌빠 문자열을 넘겨 받아 user 변수에 저장 (사용자가 묵찌빠 중 하나 선택)
         if(user.equals("stop")) { 
            System.out.println();
            return;
         } else if (!user.equals("m") && !user.equals("j") && !user.equals("b")) {
            System.out.println("Select one among m, j, b.");
            continue;
         }
         
         String computer = MJBarray[random.nextInt(MJBarray.length)];
         
         System.out.println("User = " + user + ", Computer = " + computer + ", ");
         
         if(user.equals(computer)) {
            System.out.println(priStr[priority] + " WINs."); 
         } else {
            System.out.println("SAME.");
            
            if((user.equals("m") && computer.equals("j")) ||
              (user.equals("j") && computer.equals("b")) ||
              (user.equals("b") && computer.equals("m"))) {
               priority = USER;
            } else {
               priority = COMPUTER;
            }
         }
          // 사용자가 입력한 문자열을 비교할 때는 if (user.equals("stop")) 문장을 사용 
         // 사용자가 m, j, b를 입력하지 않고 다른 문자열을 입력했다면 
          //    "Select one among m, j, b."를 출력하고 while()의 처음부터 다시 시작
          // [0,1,2] 난수를 이용하여 COMPUTER가 묵찌빠 중 하나를 선택함
       //   String computer = MJBarray[random.nextInt(MJBarray.length)];
          //user와 computer 변수를 이용하여 화면에 사용자, 컴퓨터가 낸 묵찌빠 값을 출력 
             // (예:User = m, Computer = b, )
         // 이후 user와 computer 문자열을 비교하여 같으면 
              // user와 computer 두 문자열 비교하는 방법: if (user.equals(computer)) 
              // 우선권을 가진 사람의 묵찌빠와 동일한 묵찌빠를 상대방이 냈을 경우 우선권을 가진 사람이 승리함
            //  우선권을 가진 자(priStr[priority])가 " WINs." 했다고 출력하고
         // 같지 않으면 (비겼음) 
           //   "SAME." 출력
           //   현 상황을 (가위바위보)라 가정하고 누가 이겼는지 판단하여 우선권(priority)을 가진자 결정 
           //   즉, priority 값을 가위바위보상 이긴 자(USER 또는 COMPUTER)로 변경함
           //   (아주 긴 조건문이 될 것임)
         
      }
  }
}

//===============================================================================
// class Ch2
//===============================================================================
class Ch2 
{
   public static final int MENU_COUNT = 6;
   
    public static void run() {
        String menuStr =
             "************* Ch2 Menu ***********\n" +
             "* 0.exit 1.output 2.readToken    *\n" +
             "* 3.readLine 4.operator 5.switch *\n" +
             "**********************************\n";

        
        int menuItem;
        while(true) {
           menuItem = UI.selectMenu("\n"+menuStr, MENU_COUNT);
           switch(menuItem) {
           case 0: 
              return;
           case 1:
              output();
              break;
           case 2:
              readToken();
              break;
           case 3:
              readLine();
              break;
           case 4:
              operator();
              break;
           case 5:
              switchCase();
              break;
           default:
              System.out.println("menuItem: " + menuItem);
              break;
           }
        }
    }

    public static void output()     {
        String toolName = "JDK";
         double version = 1.8;
         String released = "is released.";

         System.out.println(toolName + version + released);
         System.out.println(toolName + " " + version + " " + released);

         int i1 = 1, i2 = 2, i3 = 3; // 변수 선언과 함께 초기화

         System.out.println(i1+i2+i3);
         System.out.println(i1+""+i2+i3);
         System.out.println(i1+i2+i3+" "+i1+""+i2+i3);

         boolean b = true;
         double d = 1.2;

         System.out.println(b+" " + !b + " " + d);
    }
    
    public static void readToken()  {
       String  name;    // 이름
        int     id;      // Identifier
        double  weight;  // 체중
        boolean married; // 결혼여부
        String  address; // 주소

    
        System.out.println("person information(name id weight married :address:):");
     
        name = UI.scan.next();
        id = UI.scan.nextInt();
        weight = UI.scan.nextDouble();
        married = UI.scan.nextBoolean();

        // 주소의 패턴 ":address:"을 읽어 들임: 이미 완성된 코드이므로 아래 address를 바로 활용하면 됨
        while ((address = UI.scan.findInLine(":.*:")) == null)
           UI.scan.nextLine();  // 현재 행에 주소가 없다면 그 행을 스킵함
        address = address.substring(1, address.length()-1); // 주소 양 끝의 ':' 문자 삭제

        System.out.println(name + " " + id + " " + weight + " " + married + " :" + address + ":");
    }
    
    public static void readLine()   {
       String name = UI.getNext("name? "); // "name? "을 출력한 후 이름을 입력 받음
       System.out.println("name: " + name);
        int id = UI.getInt("id? ");         // "id? "을 출력한 후 id을 입력 받음
        System.out.println("id: " + id);
        
        String address = UI.getNextLine("address? ");// "address? " 출력 후 한줄 전체 입력받음
        System.out.println("address :" + address+":");
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
        b = 0b11111111_00000000_00000000_11111111;
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
                }
           
    }
}
}
