package com.lzh.interview.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Created by lizhuohang on 2016/12/18.
 *
 * Use SoftReference to realize a high speed cache
 */
public class EmployeeCache {
    private class Employee{
        private String id;
        private String name;
        private String department;
        private String phone;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Employee(String id) {
            this.id = id;
            getDataFromlnfoCenter();
        }
        private void getDataFromlnfoCenter() {
            // get employee from db then set the info into the entity
        }
    }

    private class EmployeeRef extends SoftReference<Employee>{
        private String _key = "";

        public EmployeeRef(Employee employee, ReferenceQueue<? super Employee> q) {
            super(employee, q);
            _key = employee.getId();
        }
    }

    private static EmployeeCache cache;
    private Hashtable<String , EmployeeRef> employeeRefs;
    private ReferenceQueue<Employee> q;


    private EmployeeCache(){
        employeeRefs = new Hashtable<String, EmployeeRef>();
        q = new ReferenceQueue<Employee>();
    }

    public static EmployeeCache getInstance(){
        if (cache == null){
            synchronized (cache){
                if (cache == null){
                    cache = new EmployeeCache();
                }
            }
        }

        return cache;
    }

    private void cacheEmployee(Employee employee){
        cleanCache();
        EmployeeRef ref = new EmployeeRef(employee , q);
        employeeRefs.put(employee.getId() , ref);
    }

    public Employee getEmployee(String id){
        Employee employee = null;
        if (employeeRefs.contains(id)){
            EmployeeRef ref = employeeRefs.get(id);
            employee = ref.get();
        }
        // this employee entity has been sweep from memory if the employee is got from the hash table but is null
        if (employee == null){
            employee = new Employee(id);
            System.out.println("Retrieve From DB , id is " + id);
            this.cacheEmployee(employee);
        }
        return employee;
    }

    private void cleanCache(){
        EmployeeRef ref = null;
        while((ref = (EmployeeRef)q.poll()) != null){
            employeeRefs.remove(ref._key);
        }
    }

    public void clearCache(){
        cleanCache();
        employeeRefs.clear();
        System.gc();
        System.runFinalization();
    }
}
