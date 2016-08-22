import java.util.ArrayList;
import java.util.List;

public class Person {

    String name;
    String surname;
    Long id;
    String city;
    String number;
    Person parent;
    final static Person RootPerson = new Person(null, "root", "root", 1L, "istanbul", "007");

    //todo read, all children of this person(parent) must be held in this parameter
    List<Person> children = new ArrayList<Person>();

    public Person(Person parent, String name, String surname, Long id, String city, String number) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.city = city;
        this.number = number;
        this.parent = parent;

        //todo validate all parameters here for empty/null strings by calling isStringEmpty method
    }

    public static void main(String[] args) throws DuplicateChildException, ChildNotFoundException, RootCannotBeDeleted {
        //todo add children of RootPerson parameter by using the constructor and addChild method
        Person Ali = new Person(RootPerson,"ali","1",2L,"istanbul","23425");
        Person Mehmet = new Person(RootPerson,"mehmet","2",3L,"istanbul","33425");
        Person Veli = new Person(Ali,"veli","3",4L,"istanbul","43425");
        Person Huseyin = new Person(Ali,"hüseyin","1",5L,"istanbul","53425");
        RootPerson.addChild(Ali);
        Ali.addChild(Veli);
        Ali.addChild(Huseyin);
        getChildrenHierarchyAsList("veli");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return name.equals(person.name);
    }

    public void removeMe() throws RootCannotBeDeleted {
        this.parent=null;
        this.parent.children.remove(this);
        //todo remove this child from its parent
    }

    public Boolean isStringEmpty(String string) {
        //todo implement for checking whether string is empty or not
        if(string==null || string.trim().equals(""))
            return true;
        return false;
    }

    public Person findThePerson(String name) {
        for(Person child:this.children) {
            if(child.name.equals(name))
                return child;
            else{
                return child.findThePerson(name);
            }
        }
        //todo implement for finding the child you must starting from this person's children and downward in the hierarchy
        //See equals method and use it here
        //See overloaded findThePersonGlobally method below
        return null;
    }

    public static Person findThePersonGlobally(String name) {
        //todo implement here
        Person a = RootPerson.findThePerson(name);
        return a;
    }

    public void addChild(Person child) throws DuplicateChildException {
        //todo implement for adding children to this object
        //don't add a child twice with same name
        //see overloaded method below
        this.children.add(child);
    }

    public static void addChild(String parentName, Person child) throws DuplicateChildException {
        //todo implement for adding children to this object
        //don't add a child twice
        //make sure all names are unique globally!
        Person parent=findThePersonGlobally(parentName);
        if (parent==null)
            System.out.println("Parent nesnesi bulunamadı.");
        else{
            parent.children.add(child);
        }
    }

    public void removeChild(Person child) throws ChildNotFoundException, RootCannotBeDeleted  {
        //todo implement for removing the child you must look downward all children in the hierarchy, see findThePerson function
        //don't do anything if the child does not exist in the Hierarchy
        if(child==RootPerson) {
            throw new RootCannotBeDeleted("Root silinemez !");

        }
        else if(child==this){
            child.parent.children.remove(this);
            child.parent=null;
        }
        else{
             if(this.findThePerson(child.name)==null) {
                 throw new ChildNotFoundException(child.name+" bulunamadı");

             }
             else{
                 Person q = this.findThePerson(child.name);
                 q.parent.children.remove(q);
                 q.parent=null;
             }
        }
    }

    public static void removeChildGlobally(Person child) throws ChildNotFoundException,RootCannotBeDeleted {
        //todo implement for removing child, see removeMe function
        //don't do anything if the child does not exist for this parent
        //see overloaded function
        if(child==RootPerson){
            System.out.println("Root silinemez !");
            return;
        }
        else{
            if(findThePersonGlobally(child.name)!=null)
                findThePersonGlobally(child.name).removeMe();
            else{
                System.out.println("Silinecek nesne bulunamadı.");
                return;
            }
        }
    }


    public static List<Person> getChildrenHierarchyAsList(String childName) {
        //todo return a list starting from parent to child like root->ali->Veli, see the overloaded function also below
        Person Q = findThePersonGlobally(childName);
        List<Person> liste = new ArrayList<Person>();
        if(Q==null){
            System.out.println("Listelenecek nesne yok !");
            return null;
        }
        else{
            Person tmp = Q;
            while (tmp != null) {
                liste.add(tmp);
                tmp = tmp.parent;
            }
            for(Person p:liste){
                System.out.print(">"+p.name);
            }
            System.out.println();
            return null;
        }
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public Person getParent() {
        return parent;
    }

    public static Person getRootPerson() {
        return RootPerson;
    }

    public List<Person> getChildren() {
        return children;
    }
}
