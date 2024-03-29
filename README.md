[![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/Naereen/)
[![Open Source? Yes!](https://badgen.net/badge/Open%20Source%20%3F/Yes%21/blue?icon=github)](https://github.com/Naereen/badges/)
[![MIT license](https://img.shields.io/badge/License-MIT-blue.svg)](https://lbesson.mit-license.org/)
[![CircleCI](https://circleci.com/gh/reol224/Vushboard/tree/main.svg?style=shield)](https://circleci.com/gh/reol224/Vushboard/tree/main)

# Vushboard
<em>If build is failing, means that MongoDB is on pause.</em>

Vushboard (VD) is an open source security auditing utility. 

It works by collecting inventory of softwares and packages installed on remote infrastructure endpoints (Windows 10, all editions of Windows Server and
Red Hat linux are supported for now) and use publicly available vulnerability feeds to do a vulnerability asssessment of the installed packages.  

It provides users with the ability to triage CVE, create remediation plans based on priorities (by vulnerability severity, logical environment...) and more generally do a Vulnerability Risk Assessment of their infrastructure.

Thus, using one of the core functionnalities of vendor security solutions used by security engineers, and penetration testers (and non-ethical hackers...)


## Basic System Diagram
<img src ="img/vulnerabilityDashboard.jpg">

## Input Example :


#### Windows Software 
On a Windows System,  
Open powershell and type   
```powershell
Get-WmiObject -Class Win32_Product
```

```powershell
IdentifyingNumber : {6AD2231F-FF48-4D59-AC26-405AFAE23DB7}
Name              : ManageEngine Desktop Central - Agent
Vendor            : ZohoCorp
Version           : 10.0.633.W
Caption           : ManageEngine Desktop Central - Agent

IdentifyingNumber : {1B1CFE9F-D421-4193-ACB8-FDE4D565C715}
Name              : Oracle VM VirtualBox 6.1.14
Vendor            : Oracle Corporation
Version           : 6.1.14
Caption           : Oracle VM VirtualBox 6.1.14
```
### Linux packages 

#### How to read a Linux package
example : subscription-manager-1.24.42-1.el7.x86_64  
(separate at the "-")  
Package Name : subscription-manager  
Package Version : 1.24.42  
Package Release : 1.el7  
Package Architecture : x86_64  
(Package Type : implied. Always RPM for a Fedora / CentOS or Red Hat system)  

### Example from Red Hat linux 7 system
On a red hat system, open CLI and type :
```bash
rpm -qa 
```
```bash
irqbalance-1.0.7-12.el7.x86_64
python-six-1.9.0-2.el7.noarch
libldb-1.5.4-1.el7.x86_64
libxcb-1.13-1.el7.x86_64
python-backports-1.0-8.el7.x86_64
redhat-release-server-7.9-5.el7_9.x86_64
python-inotify-0.9.4-4.el7.noarch
gpg-pubkey-352c64e5-52ae6884
glibc-common-2.17-317.el7.x86_64
gpg-pubkey-04bbaa7b-4c881cbf
openssl-libs-1.0.2k-21.el7_9.x86_64
....
```

## Example of querying the Red Hat Vulnerability Database
Base URL : https://access.redhat.com/hydra/rest/securitydata/cve

Documentation : https://access.redhat.com/documentation/en-us/red_hat_security_data_api/1.0/html-single/red_hat_security_data_api/index

Browsing all vulnerabilities (CVEs) affecting mariadb package (regardless of version).
This needs to be further processed to collect CVEs only affecting the current installed number and not all history, via leveraging either :
- Leverage parameters in the GET request
- Locally store in VD Database all vulnerabilities affecting packages known to be installed. Then, locally process to extract only the CVEs associated with the installed version number


<img src ="img/example-get-redh-hat-vulnerabilities-by-pkg.jpg">
