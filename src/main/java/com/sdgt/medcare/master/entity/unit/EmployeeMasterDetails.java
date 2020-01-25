package com.sdgt.medcare.master.entity.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sdgt.medcare.master.customAnnotations.MastersFieldCustomAnnotation;
import com.sdgt.medcare.master.entity.BaseMaster;
import com.sdgt.medcare.master.entity.global.EmployeeContactDetails;
import com.sdgt.medcare.master.entity.global.EmployeeEducationDetails;
import com.sdgt.medcare.master.entity.global.EmployeeProfessionDetails;
import com.sdgt.medcare.master.entity.global.GenderMaster;
import com.sdgt.medcare.master.entity.global.IdentificationTypeMaster;
import com.sdgt.medcare.master.entity.global.MaritalStatusMaster;
import com.sdgt.medcare.master.entity.global.NationalityMaster;
import com.sdgt.medcare.master.entity.global.OTRoleMaster;
import com.sdgt.medcare.master.entity.global.OccupationMaster;
import com.sdgt.medcare.master.entity.global.PrefixMaster;
import com.sdgt.medcare.master.entity.global.QuePrefixMaster;
import com.sdgt.medcare.master.entity.global.RaceMaster;
import com.sdgt.medcare.master.entity.org.CabinMaster;
import com.sdgt.medcare.master.entity.org.DepartmentMaster;
import com.sdgt.medcare.master.entity.org.DoctorTypeMaster;
import com.sdgt.medcare.master.entity.org.EmployeeTypeMaster;
import com.sdgt.medcare.master.entity.org.FacultyMaster;
import com.sdgt.medcare.master.entity.org.GradeMaster;
import com.sdgt.medcare.master.entity.org.OrganizationMaster;
import com.sdgt.medcare.master.entity.org.SubDepartmentMaster;
import com.sdgt.medcare.master.entity.org.UnitMaster;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@JsonDeserialize
@Table (name = "m_employee_details", schema = "employee")
public class EmployeeMasterDetails extends BaseMaster {
   @JsonIgnoreProperties ({"organizationMaster", "countryMaster"})
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "unitMaster_id")
   private UnitMaster unitMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "DoctorTypeMaster_id")
   private DoctorTypeMaster doctorTypeMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "employeeTypeMaster_id")
   private EmployeeTypeMaster employeeTypeMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "prefixMaster_id")
   private PrefixMaster prefixMaster;

   @Column
   private String remarks;
   @Column
   private String name;

   @Column
   private String otherName;

   @Column (name = "employee_no")
   private String employeeNo;

   @Column (name = "redg_no")
   private String redgNo;


   @Column (name = "expire_date")
   private Date expireDate;
   @Column (name = "emp_age")
   private String age;

   @Column (name = "emp_dob")
   private Date dob;


   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "GenderMaster_id")
   private GenderMaster genderMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "MaritalStatusMaster_id")
   private MaritalStatusMaster maritalStatus;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "nationalityMaster_id")
   private NationalityMaster nationalityMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "OccupationMaster_id")
   private OccupationMaster occupationMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "raceMaster_id")
   private RaceMaster raceMaster;


   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "identificationTypeMaster_id")
   private IdentificationTypeMaster identificationTypeMaster;

   @Column (name = "identification_number")
   private String identificationNumber;

   @Column (name = "doctor_image", length = 10485760)
   private String doctorImage;

   @Column (name = "digital_sign", length = 10485760)
   private String digitalSign;

   @Column (name = "email")
   private String email;

   @Column (name = "mobile_number")
   private String mobileNumber;


   @Column (name = "emp_img")
   @Type (type = "org.hibernate.type.BinaryType")
   private byte[] empImage;


   @Column (name = "emp_sign")
   @Type (type = "org.hibernate.type.BinaryType")
   private byte[] empSign;

   public String getDoctorPrefix() {
      return doctorPrefix;
   }
   public void setDoctorPrefix(String doctorPrefix) {
      this.doctorPrefix = doctorPrefix;
   }

   @Column (name = "doc_prefix", length = 50)
   private String doctorPrefix;
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "departmentMaster_id")
   private DepartmentMaster departmentMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "subDepartmentMaster_id")
   private SubDepartmentMaster subDepartmentMaster;

   @Column (name = "over_booked_slot")
   private Integer overBookedSlot;

   @Column (name = "allowed_over_booking_slots")
   private String allowedOverBookingSlots;

   @Column (name = "is_gst_applicable")
   private Boolean isGSTApplicable;

   @Column (name = "is_user")
   private String isUser;

   @Column
   private Boolean isUserReq;

   @Column
   private Boolean isOverSlotReq;

   public Boolean getIsUserReq() {
      return isUserReq;
   }

   public void setIsUserReq(Boolean isUserReq) {
      this.isUserReq = isUserReq;
   }

   public Boolean getIsOverSlotReq() {
      return isOverSlotReq;
   }

   @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeMasterDetails")
   @JsonIgnoreProperties ({"employeeMasterDetails", "listDependentDetails", "employeeContactDetails"})
   @JoinColumn (name = "employee_contactDetails")
   private EmployeeContactDetails employeeContactDetails;

   @JsonIgnoreProperties ({"employeeMasterDetails", "listEmployeeEducationDetails"})
   @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeMasterDetails")
   private List<EmployeeEducationDetails> listEmployeeEducationDetails;

   @JsonIgnoreProperties ({"employeeMasterDetails", "listEmployeeProfessionDetails"})
   @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeMasterDetails")
   private List<EmployeeProfessionDetails> listEmployeeProfessionDetails;

   @JsonIgnoreProperties ({"employeeMasterDetails", "listDependentDetails"})
   @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeMasterDetails")
   private List<DependentDetails> listDependentDetails;

   @JsonIgnoreProperties ({"employeeMasterDetails", "empDeptDetailsList"})
   @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employeeMasterDetails")
   private List<EmployeeDepartmentDetails> empDeptDetailsList;


   @JsonIgnoreProperties ({"unitMasterList", "orgLogo", "emailId"})
   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "OrganizationMaster_id")
   private OrganizationMaster organizationMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "CabinMaster")
   private CabinMaster cabinMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "grade_master_id")
   private GradeMaster gradeMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "que_prefix_master")
   private QuePrefixMaster quePrefixMaster;

   public Boolean getGSTApplicable() {

      return isGSTApplicable;
   }

   public void setGSTApplicable(Boolean GSTApplicable) {

      isGSTApplicable = GSTApplicable;
   }

   public EmployeeContactDetails getEmployeeContactDetails() {
      return employeeContactDetails;
   }

   public void setEmployeeContactDetails(EmployeeContactDetails employeeContactDetails) {
      this.employeeContactDetails = employeeContactDetails;
   }

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "FacultyMaster_id")
   private FacultyMaster facultyMaster;

   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn (name = "OTRoleMaster_id")
   private OTRoleMaster otRoleMaster;

   @ManyToMany (fetch = FetchType.LAZY)
   @JoinTable (name = "unitmaster_employeemaster_mapper", joinColumns = @JoinColumn (name = "UnitMaster_id"), inverseJoinColumns = @JoinColumn (name = "EmployeeMasterDetails_id"))
   private List<UnitMaster> listunitmaster;


   @Column (name = "is_doctorshare")
   @MastersFieldCustomAnnotation (displayName = "Is Doctor Share")
   private Boolean isdoctorshare;

   @JsonIgnoreProperties
   @JsonIgnore
   @ManyToMany (fetch = FetchType.LAZY)
   @JoinTable (name = "employee_unitmaster_mapper", joinColumns = @JoinColumn (name = "employee_master_id"), inverseJoinColumns = @JoinColumn (name = "unit_master_id"))
   private List<UnitMaster> unitMasterList;


   /*@ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name="emp_dept_mapper",joinColumns =@JoinColumn(name = "employee_master"),inverseJoinColumns = @JoinColumn(name="department_master"))
   private List<EmployeeDepartmentDetails>  employeeDepartmentDetailsList;*/
   public UnitMaster getUnitMaster() {

      return unitMaster;
   }

   public void setUnitMaster(UnitMaster unitMaster) {
      this.unitMaster = unitMaster;
   }

   public DoctorTypeMaster getDoctorTypeMaster() {
      return doctorTypeMaster;
   }

   public void setDoctorTypeMaster(DoctorTypeMaster doctorTypeMaster) {
      this.doctorTypeMaster = doctorTypeMaster;
   }

   public PrefixMaster getPrefixMaster() {
      return prefixMaster;
   }

   public void setPrefixMaster(PrefixMaster prefixMaster) {
      this.prefixMaster = prefixMaster;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getOtherName() {
      return otherName;
   }

   public void setOtherName(String otherName) {
      this.otherName = otherName;
   }

   public String getEmployeeNo() {
      return employeeNo;
   }

   public void setEmployeeNo(String employeeNo) {
      this.employeeNo = employeeNo;
   }

   public GenderMaster getGenderMaster() {
      return genderMaster;
   }

   public void setGenderMaster(GenderMaster genderMaster) {
      this.genderMaster = genderMaster;
   }

   public NationalityMaster getNationalityMaster() {
      return nationalityMaster;
   }

   public void setNationalityMaster(NationalityMaster nationalityMaster) {
      this.nationalityMaster = nationalityMaster;
   }

   public OccupationMaster getOccupationMaster() {
      return occupationMaster;
   }

   public void setOccupationMaster(OccupationMaster occupationMaster) {
      this.occupationMaster = occupationMaster;
   }

   public RaceMaster getRaceMaster() {
      return raceMaster;
   }

   public void setRaceMaster(RaceMaster raceMaster) {
      this.raceMaster = raceMaster;
   }

   public IdentificationTypeMaster getIdentificationTypeMaster() {
      return identificationTypeMaster;
   }

   public void setIdentificationTypeMaster(IdentificationTypeMaster identificationTypeMaster) {
      this.identificationTypeMaster = identificationTypeMaster;
   }

   public String getIdentificationNumber() {
      return identificationNumber;
   }

   public void setIdentificationNumber(String identificationNumber) {
      this.identificationNumber = identificationNumber;
   }

   public String getDoctorImage() {
      return doctorImage;
   }

   public void setDoctorImage(String doctorImage) {
      this.doctorImage = doctorImage;
   }

   public String getDigitalSign() {
      return digitalSign;
   }

   public void setDigitalSign(String digitalSign) {
      this.digitalSign = digitalSign;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getMobileNumber() {
      return mobileNumber;
   }

   public void setMobileNumber(String mobileNumber) {
      this.mobileNumber = mobileNumber;
   }


   public SubDepartmentMaster getSubDepartmentMaster() {
      return subDepartmentMaster;
   }

   public void setSubDepartmentMaster(SubDepartmentMaster subDepartmentMaster) {
      this.subDepartmentMaster = subDepartmentMaster;
   }


   public Integer getOverBookedSlot() {
      return overBookedSlot;
   }

   public void setOverBookedSlot(Integer overBookedSlot) {
      this.overBookedSlot = overBookedSlot;
   }

   public String getAllowedOverBookingSlots() {
      return allowedOverBookingSlots;
   }

   public void setAllowedOverBookingSlots(String allowedOverBookingSlots) {
      this.allowedOverBookingSlots = allowedOverBookingSlots;
   }

   public Boolean getIsGSTApplicable() {

      return isGSTApplicable;
   }

   public void setIsGSTApplicable(Boolean isGSTApplicable) {

      this.isGSTApplicable = isGSTApplicable;
   }

   public List<EmployeeEducationDetails> getListEmployeeEducationDetails() {
      return listEmployeeEducationDetails;
   }

   public void setListEmployeeEducationDetails(List<EmployeeEducationDetails> listEmployeeEducationDetails) {
      this.listEmployeeEducationDetails = listEmployeeEducationDetails;
   }

   public List<DependentDetails> getListDependentDetails() {
      return listDependentDetails;
   }

   public void setListDependentDetails(List<DependentDetails> listDependentDetails) {
      this.listDependentDetails = listDependentDetails;
   }

   public EmployeeTypeMaster getEmployeeTypeMaster() {
      return employeeTypeMaster;
   }

   public void setEmployeeTypeMaster(EmployeeTypeMaster employeeTypeMaster) {
      this.employeeTypeMaster = employeeTypeMaster;
   }

   public DepartmentMaster getDepartmentMaster() {
      return departmentMaster;
   }

   public void setDepartmentMaster(DepartmentMaster departmentMaster) {
      this.departmentMaster = departmentMaster;
   }

   public String getIsUser() {
      return isUser;
   }

   public void setIsUser(String isUser) {
      this.isUser = isUser;
   }

   public OrganizationMaster getOrganizationMaster() {
      return organizationMaster;
   }

   public void setOrganizationMaster(OrganizationMaster organizationMaster) {
      this.organizationMaster = organizationMaster;
   }

   public List<UnitMaster> getListunitmaster() {
      return listunitmaster;
   }

   public void setListunitmaster(List<UnitMaster> listunitmaster) {
      this.listunitmaster = listunitmaster;
   }

   public CabinMaster getCabinMaster() {
      return cabinMaster;
   }

   public void setCabinMaster(CabinMaster cabinMaster) {
      this.cabinMaster = cabinMaster;
   }


   public FacultyMaster getFacultyMaster() {
      return facultyMaster;
   }

   public void setFacultyMaster(FacultyMaster facultyMaster) {
      this.facultyMaster = facultyMaster;
   }

   public MaritalStatusMaster getMaritalStatus() {
      return maritalStatus;
   }

   public void setMaritalStatus(MaritalStatusMaster maritalStatus) {
      this.maritalStatus = maritalStatus;
   }

   public String getRedgNo() {
      return redgNo;
   }

   public void setRedgNo(String redgNo) {
      this.redgNo = redgNo;
   }


   public void setOtRoleMaster(OTRoleMaster otRoleMaster) {
      this.otRoleMaster = otRoleMaster;
   }

   public List<EmployeeDepartmentDetails> getEmpDeptDetailsList() {
      return empDeptDetailsList;
   }

   public void setEmpDeptDetailsList(List<EmployeeDepartmentDetails> empDeptDetailsList) {
      this.empDeptDetailsList = empDeptDetailsList;
   }

   public List<EmployeeProfessionDetails> getListEmployeeProfessionDetails() {
      return listEmployeeProfessionDetails;
   }

   public void setListEmployeeProfessionDetails(List<EmployeeProfessionDetails> listEmployeeProfessionDetails) {
      this.listEmployeeProfessionDetails = listEmployeeProfessionDetails;
   }

    /* public List<EmployeeDepartmentDetails> getEmployeeDepartmentDetailsList() {

        return employeeDepartmentDetailsList;
    }

    public void setEmployeeDepartmentDetailsList(List<EmployeeDepartmentDetails> employeeDepartmentDetailsList) {
        this.employeeDepartmentDetailsList = employeeDepartmentDetailsList;
    }*/

   public QuePrefixMaster getQuePrefixMaster() {
      return quePrefixMaster;
   }

   public void setQuePrefixMaster(QuePrefixMaster quePrefixMaster) {
      this.quePrefixMaster = quePrefixMaster;
   }

   public Date getExpireDate() {
      return expireDate;
   }

   public void setExpireDate(Date expireDate) {
      this.expireDate = expireDate;
   }

   public String getAge() {
      return age;
   }

   public void setAge(String age) {
      this.age = age;
   }

   public Date getDob() {
      return dob;
   }

   public void setDob(Date dob) {
      this.dob = dob;
   }

   public String getRemarks() {
      return remarks;
   }

   public void setRemarks(String remarks) {
      this.remarks = remarks;
   }

   public GradeMaster getGradeMaster() {
      return gradeMaster;
   }

   public void setGradeMaster(GradeMaster gradeMaster) {
      this.gradeMaster = gradeMaster;
   }

   public Boolean isIsdoctorshare() {
      return isdoctorshare;
   }

   public void setIsdoctorshare(Boolean isdoctorshare) {
      this.isdoctorshare = isdoctorshare;
   }

   public OTRoleMaster getOtRoleMaster() {
      return otRoleMaster;
   }

   public byte[] getEmpImage() {
      return empImage;
   }
   public void setEmpImage(byte[] empImage) {
      this.empImage = empImage;
   }
   public byte[] getEmpSign() {
      return empSign;
   }
   public void setEmpSign(byte[] empSign) {
      this.empSign = empSign;
   }
}
