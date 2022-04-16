# 单一职责原则(SRP：The Single Responsibility Principle)
### 1. **定义**：单一职责原则可以理解为一个类只负责一个功能领域中的相应职责，或者可以定义为就一个类而言，应该只有一个引起它变化的原因。
&nbsp;
### 2. **提出原因**：为什么定义一个这样的原则，换句话说如果不是单一职责会造成什么影响。现实生活中可能有这样一种情况：*类 F中包括两个职责 f1和 f2，因为随着需求的改变，职责可能会进行修改，当 f1改变需要修改 F类时可能导致 f2 职责出现问题。*
&nbsp;
### 3. **更加科学的解释**：如果一个类拥有多于一个的职责，则这些职责就耦合到在了一起，那么就会有多于一个原因来导致这个类的变化。对于某一职责的更改可能会损害类满足其他耦合职责的能力。这样职责的耦合会导致设计的脆弱，以至于当职责发生更改时产生无法预期的破坏。
&nbsp;
### 4. **解决办法**：遵守单一职责原则，将不同的职责封装到不同的类或模块中。
&nbsp;
### 5. **优点**：如果类与方法的职责划分得很清晰，不但可以提高代码的可读性，提高系统的可维护性,更实际性地更降低了程序出错的风险，因为清晰的代码会让bug无处藏身，也有利于bug的追踪，也就是降低了程序的维护成本。

&nbsp;
### 5. **实例理解**：为了便于理解，我找了一个实例进行解释。
&nbsp;
### 创造一个员工类，这个类有员工的一些基本信息。
### **新需求**：1.判定员工在今年是否升职  2. 计算员工的薪水

### 先看下面这个设计
```C++

//================== Employee.h ==================

@interface Employee : NSObject

//============ 初始需求 ============
@property (nonatomic, copy) NSString *name;       //员工姓名
@property (nonatomic, copy) NSString *address;    //员工住址
@property (nonatomic, copy) NSString *employeeID; //员工ID

//============ 新需求 ============
//计算薪水
- (double)calculateSalary;

//今年是否晋升
- (bool)willGetPromotionThisYear;

@end

```

### 在上面的代码中创建了Employee这个员工类，并声明了3个员工信息的属性：员工姓名，地址，员工ID。接下来代码中是将两个方法直接加到了员工类里面。新需求的做法很明显的违反了单一职责原则：**因为这两个方法并不是员工本身的职责。**
&nbsp;
### ***calculateSalary这个方法的职责是属于会计部门的：薪水的计算是会计部门负责。willPromotionThisYear这个方法的职责是属于人事部门的：考核与晋升机制是人事部门负责。***
&nbsp;
### 这样做的后果是如果员工的晋升机制变了，或者税收政策等影响员工工资的因素变了，我们还需要修改当前这个类。

### 所以为了不违反单一职责原则我们需要将这两个方法（责任）分离出去，让本应该处理这类任务的类来处理。
```c++
//================== Employee.h ==================

@interface Employee : NSObject

//初始需求
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *address;
@property (nonatomic, copy) NSString *employeeID;
```
创建新的会计部门
```c++
//================== FinancialApartment.h ==================

#import "Employee.h"

//会计部门类
@interface FinancialApartment : NSObject

//计算薪水
- (double)calculateSalary:(Employee *)employee;

@end
```
创建人事部门
```c++
//================== HRApartment.h ==================

#import "Employee.h"

//人事部门类
@interface HRApartment : NSObject

//今年是否晋升
- (BOOL)willGetPromotionThisYear:(Employee*)employee;

@end
```
### **UML 类图对比**
### 未使用单一原则：
![UML1](https://github.com/LiYu0105/se_homework/blob/master/images/uml1.png)
&nbsp;
### 使用了单一原则：
![UML2](https://github.com/LiYu0105/se_homework/blob/master/images/uml2.png)

