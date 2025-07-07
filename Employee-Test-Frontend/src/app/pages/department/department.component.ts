import { Component } from '@angular/core';
import { Department } from '../../models/department';
import { DepartmentService } from '../../services/department.service';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrl: './department.component.css'
})
export class DepartmentComponent {

  departments: Department[] = [];

  department: Department = {
    deptId: 0,
    deptName: ''
  };

  isEditMode = false;

  constructor(private departmentService: DepartmentService) {}

  ngOnInit(): void {
    this.loadDepartments();
  }

  loadDepartments(): void {
    this.departmentService.getAllDepartments().subscribe(data => {
      this.departments = data;
    });
  }

  onSubmit(): void {
    if (this.isEditMode) {
      this.departmentService.updateDepartment(this.department.deptId!, this.department).subscribe(() => {
        this.loadDepartments();
        this.resetForm();
      });
    } else {
      this.departmentService.addDepartment(this.department).subscribe(() => {
        this.loadDepartments();
        this.resetForm();
      });
    }
  }

  editDepartment(dept: Department): void {
    this.department = { ...dept };
    this.isEditMode = true;
  }

  deleteDepartment(id: number): void {
    if (confirm('Are you sure you want to delete this department?')) {
      this.departmentService.deleteDepartment(id).subscribe(() => {
        this.loadDepartments();
      });
    }
  }

  resetForm(): void {
    this.department = {
      deptId: 0,
      deptName: ''
    };
    this.isEditMode = false;
  }
}
