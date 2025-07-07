import { Component, OnInit } from '@angular/core';
import { Employee } from '../../models/employee';
import { Department } from '../../models/department';
import { EmployeeService } from '../../services/employee.service';
import { DepartmentService } from '../../services/department.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrl: './employee.component.css'
})
export class EmployeeComponent implements OnInit{


  employees: Employee[] = [];
  departments: Department[] = [];

  employee: Employee = {
    firstName: '',
    lastName: '',
    gender: '',
    city: '',
    phoneNo: '',
    department: {
      deptId: 0,
      deptName: ''

    }
  };

  //  For search and filter
  searchText: string = '';
  selectedDeptId: string = '';

  isEditMode = false;

  constructor(
    private employeeService: EmployeeService,
    private departmentService: DepartmentService
  ) {}

  ngOnInit(): void {
    this.loadEmployees();
    this.loadDepartments();
  }

  loadEmployees(): void {
    this.employeeService.getAllEmployees().subscribe(data => {
      this.employees = data;
    });
  }

  loadDepartments(): void {
    this.departmentService.getAllDepartments().subscribe(data => {
      this.departments = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.employeeService.updateEmployee(this.employee.empId!, this.employee).subscribe(() => {
        this.loadEmployees();
        this.resetForm();
      });
    } else {
      this.employeeService.addEmployee(this.employee).subscribe(() => {
        this.loadEmployees();
        this.resetForm();
      });
    }
  }

  editEmployee(emp: Employee): void {
    this.employee = { ...emp };
    this.isEditMode = true;
  }

  deleteEmployee(id: number): void {
    if (confirm('Are you sure you want to delete this employee?')) {
      this.employeeService.deleteEmployee(id).subscribe(() => {
        this.loadEmployees();
      });
    }
  }

  resetForm(): void {
    this.employee = {
      firstName: '',
      lastName: '',
      gender: '',
      city: '',
      phoneNo: '',
      department: {
        deptId: 0,
        deptName: ''
      }
    };
    this.isEditMode = false;
  }


  //  Filtered list for display
  filteredEmployees(): Employee[] {
    return this.employees.filter(emp => {
      const matchesSearch = this.searchText.trim() === '' ||
        emp.firstName.toLowerCase().includes(this.searchText.toLowerCase()) ||
        emp.lastName.toLowerCase().includes(this.searchText.toLowerCase()) ||
        emp.city.toLowerCase().includes(this.searchText.toLowerCase());

      const matchesDept = !this.selectedDeptId || emp.department?.deptId == +this.selectedDeptId;

      return matchesSearch && matchesDept;
    });
  }

}
