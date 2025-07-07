import { Department } from "./department";

export interface Employee {
    empId?: number;
    firstName: string;
    lastName: string;
    gender: string;
    city: string;
    phoneNo: string;
    department: Department;

    
}
