OpenHMIS API
============

Introduction
------------

The OpenHMIS API is a web-based API for collecting data and generating
reports about sources and users of social services.

The OpenHMIS API is vendor-neutral.  It is fully open and can be
implemented by anyone writing an HMIS application, allowing their
application to process HMIS information with any source that speaks
the same API.  It is based on the the 2014 data standards from the
[U.S. Department of Housing and Urban Development
(HUD)](http://hud.gov/), and is independent from the underlying data
storage system, that is, it is not related to nor dependent on any
particular database schema.

The latest version of the API is version 3 and provides complete
coverage of the HUD 2014 data standards, though it is still in
beta-testing while it undergoes further review and validation.

For more information about the HUD 2014 standards, see:

* [HMIS Data Exchange Resources](http://www.hudhdx.info/VendorResources.aspx) (only items with "2014" or later next to them)
* [2014 HMIS Logical Model UML diagram](http://www.hudhdx.info/Resources/Vendors/4_0/HMIS_Logical_Model.pdf)
* [HUD 2014 HMIS Data Dictionary](https://www.hudexchange.info/resource/3824/hmis-data-dictionary/)
* [HUD HMIS 2014 CSV Format Specification](http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf)

Note that the OpenHMIS API has a shared origin with the [HMIS API
project](https://github.com/hmis-api/) project, and we are actively
coordinating with that API to achieve maximum possible compatibility.
Currently the OpenHMIS API is compatible with the HMIS API in almost
all of the [core
methods](http://htmlpreview.github.io/?https://github.com/hmis-api/api/blob/master/doc/hmis-api.html)
and we are working to expand the compatible area.  Interoperability is
a primary goal of OpenHMIS.

#### Historical background:

There have been two earlier versions of the OpenHMIS API, both of them
based on the HUD 2010 data standards.  Neither should be used as a
basis for new development now, since they are based on HUD 2010
instead of on HUD 2014, but we list them here for reference:

* [OpenHMIS API v1](https://drive.google.com/viewerng/viewer?a=v&pid=sites&srcid=cGNuaS5vcmd8b3BlbmhtaXN8Z3g6NGVmMWE3NzQ5OWRlOTA0Mw).  This API is implemented by the [`deprecated_v1_api`](https://github.com/PCNI/OpenHMIS/tree/deprecated_v1_api) branch, and is the API currently used by the [Homeless Helper](https://github.com/PCNI/homeless-helper) application.

* [OpenHMIS API v2](https://code.google.com/p/openciss/wiki/openCISS_API_v2).  No clients use this version and no known servers implement it.

OpenHMIS API Reference Documentation
====================================

_(Note: The latest development version of this documentation is always available [on GitHub](https://github.com/PCNI/OpenHMIS/blob/feature-compass_schema/docs/API.md).)_

The OpenHMIS API is a [RESTful
API](https://en.wikipedia.org/wiki/Representational_state_transfer)
that works as you would expect if you are familiar with other RESTful
APIs.

Requests to URLs returns JSON objects.  For intermediate URLs, these
objects are lists, and the contents of the list are sub-objects of
whatever type would be fetched by extending the URL with the natural
next component.  For example, assuming that
`http://hmis.example.com/openhmis/` is the API services base, then a
GET request to

        http://hmis.example.com/openhmis/api/v3/clients

would fetch a list of clients like this:

        [
            {"personalId":"336788",
             "firstName":"Renee",
             "middleName":null,
             "lastName":"Mover",
             "nameSuffix":null,
             "nameDataQuality":99,
             "ssn":"459834818",
             ... }
            {"personalId":"336823",
             "firstName":"Tommy",
             "middleName":null,
             "lastName":"Harbison",
             "nameSuffix":null,
             "nameDataQuality":99,
             "ssn":"106533370"," 
             ... }
            ...
        ]

Extending that with the appropriate personalId value ...

        http://hmis.example.com/openhmis/api/v3/clients/336788
    
...would fetch the corresponding individual Client:

        {"personalId":"336788",
         "firstName":"Renee",
         "middleName":null,
         "lastName":"Mover",
         "nameSuffix":null,
         "nameDataQuality":99,
         "ssn":"459834818",
         ... }

POST, PUT, and DELETE work as expected.

Note that you cannot edit fields inside nested objects.  For example,
if you PUT an Enrollment object (containing `income sources` nested
within it) to `services/enrollments/3/`, you should not expect changes
in nested income sources to take effect.  Instead, to change or add
those income sources, you must use the appropriate nested endpoint,
e.g., `services/enrollments/3/income-sources/1`.

# Top-level resources:

* Clients
* Enrollments
* Organizations
* Projects

# Clients

The Clients resource supports GET, POST, PUT, and DELETE methods.

URI: `/clients`

* GET
  - Path: `/clients/`
  - Method name: `getClients()`
  - Parameters: None
  - Responses: Returns all clients found.
  - Example:
    Call: `$ curl http://localhost:8080/openhmis/api/v3/clients/`
    Response: ```[
  {
    "dateUpdated": "2015-05-08",
    "dateCreated": "2003-03-03",
    "dischargeStatus": 99,
    "militaryBranch": 99,
    "otherTheater": 99,
    "iraqOND": 99,
    "iraqOIF": 99,
    "afghanistanOEF": 99,
    "desertStorm": 99,
    "vietnamWar": 99,
    "koreanWar": 99,
    "worldWarII": 99,
    "yearSeparated": null,
    "yearEnteredService": null,
    "veteranStatus": 0,
    "otherGender": null,
    "gender": 2,
    "ethnicity": 0,
    "raceNone": 8,
    "white": 1,
    "nativeHIOtherPacific": 0,
    "blackAfAmerican": 0,
    "asian": 0,
    "amIndAKNative": 0,
    "dobDataQuality": 1,
    "dob": "2045-01-24",
    "ssnDataQuality": 1,
    "ssn": "459834818",
    "nameDataQuality": 99,
    "nameSuffix": null,
    "lastName": "Mover",
    "middleName": null,
    "firstName": "Renee",
    "personalId": "336788"
  },
  {
    "dateUpdated": "2015-05-08",
    "dateCreated": "2005-06-28",
    "dischargeStatus": 99,
    "militaryBranch": 99,
    "otherTheater": 99,
    "iraqOND": 99,
    "iraqOIF": 99,
    "afghanistanOEF": 99,
    "desertStorm": 99,
    "vietnamWar": 99,
    "koreanWar": 99,
    "worldWarII": 99,
    "yearSeparated": null,
    "yearEnteredService": null,
    "veteranStatus": 0,
    "otherGender": null,
    "gender": 2,
    "ethnicity": 0,
    "raceNone": 8,
    "white": 1,
    "nativeHIOtherPacific": 0,
    "blackAfAmerican": 0,
    "asian": 0,
    "amIndAKNative": 0,
    "dobDataQuality": 1,
    "dob": "2049-12-31",
    "ssnDataQuality": 99,
    "ssn": "927754675",
    "nameDataQuality": 99,
    "nameSuffix": "Ms",
    "lastName": "Test",
    "middleName": "A",
    "firstName": "Sheis",
    "personalId": "518207"
  }
]```

* POST
  - Path: `/clients/`
  - Method name: `createClient()`
  - Parameters: `String data`.
    - This should be a JSON-formatted string with data element names matching those in the example above.
  - Responses: Returns the newly created client as a JSON-formatted string.
  - Example:
    Call: `curl -X POST -d '{"firstName":"secondRenee","middleName":null,"lastName":"Mover","nameSuffix":null,"nameDataQuality":99,"ssn":"459834818","ssnDataQuality":1,"dob":"2045-01-24","dobDataQuality":1,"amIndAKNative":0,"asian":0,"blackAfAmerican":0,"nativeHIOtherPacific":0,"white":1,"raceNone":8,"ethnicity":0,"gender":2,"otherGender":null,"veteranStatus":0,"yearEnteredService":null,"yearSeparated":null,"worldWarII":99,"koreanWar":99,"vietnamWar":99,"desertStorm":99,"afghanistanOEF":99,"iraqOIF":99,"iraqOND":99,"otherTheater":99,"militaryBranch":99,"dischargeStatus":99,"dateCreated":"2003-03-03","dateUpdated":"2015-05-08"}' 'http://localhost:8080/openhmis/api/v3/clients/'`
    Response: ```{
  "dateUpdated": 1438811623501,
  "dateCreated": 1438811623501,
  "dischargeStatus": 99,
  "militaryBranch": 99,
  "otherTheater": 99,
  "iraqOND": 99,
  "iraqOIF": 99,
  "afghanistanOEF": 99,
  "desertStorm": 99,
  "vietnamWar": 99,
  "koreanWar": 99,
  "worldWarII": 99,
  "yearSeparated": null,
  "yearEnteredService": null,
  "veteranStatus": 0,
  "otherGender": null,
  "gender": 2,
  "ethnicity": 0,
  "raceNone": 8,
  "white": 1,
  "nativeHIOtherPacific": 0,
  "blackAfAmerican": 0,
  "asian": 0,
  "amIndAKNative": 0,
  "dobDataQuality": 1,
  "dob": 2368828800000,
  "ssnDataQuality": 1,
  "ssn": "459834818",
  "nameDataQuality": 99,
  "nameSuffix": null,
  "lastName": "Mover",
  "middleName": null,
  "firstName": "secondRenee",
  "personalId": "715944"
}```


* GET:
  - Path: `/clients/{personalId}`
  - Method name: `getClient("personalId")`
  - Parameters: Takes a `personalId`.
  - Responses: Returns a single client with `personalId` matching the parameter passed in.
  - Example:
    - Call: `$ curl http://localhost:8080/openhmis/api/v3/clients/336788`
    - Response: ```{
  "dateUpdated": "2015-05-08",
  "dateCreated": "2003-03-03",
  "dischargeStatus": 99,
  "militaryBranch": 99,
  "otherTheater": 99,
  "iraqOND": 99,
  "iraqOIF": 99,
  "afghanistanOEF": 99,
  "desertStorm": 99,
  "vietnamWar": 99,
  "koreanWar": 99,
  "worldWarII": 99,
  "yearSeparated": null,
  "yearEnteredService": null,
  "veteranStatus": 0,
  "otherGender": null,
  "gender": 2,
  "ethnicity": 0,
  "raceNone": 8,
  "white": 1,
  "nativeHIOtherPacific": 0,
  "blackAfAmerican": 0,
  "asian": 0,
  "amIndAKNative": 0,
  "dobDataQuality": 1,
  "dob": "2045-01-24",
  "ssnDataQuality": 1,
  "ssn": "459834818",
  "nameDataQuality": 99,
  "nameSuffix": null,
  "lastName": "Mover",
  "middleName": null,
  "firstName": "Renee",
  "personalId": "336788"
}```


* PUT:
  -Path: `/clients/{personalId}`
  - Method name: `updateClient("personalId")`
  - Parameters: Takes a `personalId` and data corresponding to the fields to be updated.
  - Responses: Returns a JSON-formatted string with data for the updated client.
  - Example:
    - Call: ``
    - Response: ``

* DELETE:
  - Path: `/clients/{personalId}`
  - Method name: `deleteClient("personalId")`
  - Parameters: Takes a `personalId` for the client to be deleted.
  - Responses: Returns `true` if the client was successfully deleted.
  - Example:
    - Call: `$ curl -X DELETE http://localhost:8080/openhmis/api/v3/clients/336788`
    - Response: `true`


# Enrollments

* GET:
  - Path: `/enrollments`
  - Method name: `getEnrollments()`
  - Parameters: None
  - Responses: Returns all enrollments.
  - Example:
    - Call: `$ curl http://localhost:8080/openhmis/api/v3/enrollments`
    - Response: ```[{
    "dateUpdated": "2015-05-08",
    "dateCreated": "2005-06-08",
    "addressDataQuality": 99,
    "lastPermanentZip": "",
    "lastPermanentState": "",
    "lastPermanentCity": "",
    "lastPermanentStreet": "",
    "percentAmi": 99,
    "worstHousingSituation": 99,
    "medicalAssistances": [],
    "askedOrForcedToExchangeForSex": 99,
    "countOfExchangeForSex": 99,
    "exchangeForSexPastThreeMonths": 99,
    "countOUtreachReferralApproaches": 0,
    "referralSource": 99,
    "incarceratedParentStatus": 99,
    "incarceratedParent": 99,
    "activeMilitaryParent": 99,
    "insufficientIncome": 99,
    "alcoholDrugAbuseFam": 99,
    "alcoholDrugAbuseYouth": 99,
    "abuseAndNeglectFam": 99,
    "abuseAndNeglectYouth": 99,
    "mentalDisabilityFam": 99,
    "mentalDisabilityYouth": 99,
    "physicalDisabilityFam": 99,
    "physicalDisabilityYouth": 99,
    "healthIssuesFam": 99,
    "healthIssuesYouth": 99,
    "mentalHealthIssuesFam": 99,
    "mentalHealthIssuesYouth": 99,
    "unemploymentFam": 99,
    "unemploymentYouth": 99,
    "schoolEducationalIssuesFam": 99,
    "schoolEducationalIssuesYouth": 99,
    "housingIssuesFam": 99,
    "housingIssuesYouth": 99,
    "sexualOrientationGenderIdFam": 99,
    "sexualOrientationGenderIdYouth": 99,
    "householdDynamics": 99,
    "juvenileJusticeMonths": 0,
    "juvenileJusticeYears": 99,
    "formerWardJuvenileJustice": 99,
    "childWelfareMonths": 0,
    "childWelfareYears": 99,
    "formerlyChildWelfare": 99,
    "pregnancyDueDate": 1438812702284,
    "pregnancyStatusCode": 99,
    "mentalHealthStatus": 99,
    "dentalHealthStatus": 99,
    "generalHealthStatus": 99,
    "notEmployedReason": 99,
    "employmentType": 99,
    "employed": 99,
    "employedInformationDate": 1438812702284,
    "schoolStatus": 99,
    "lastGradeCompleted": 99,
    "sexualOrientation": 99,
    "reasonNoServices": 99,
    "fysbYouth": 99,
    "dateOfBcpStatus": 1438812702284,
    "reasonNotEnrolled": 99,
    "clientEnrolledInPath": 99,
    "dateOfPathStatus": 1438812702284,
    "permanentHousingMoveDate": 1438812702284,
    "inPermanentHousing": 99,
    "residentialMoveInDate": 1438812702284,
    "referrals": [],
    "financialAssistances": [],
    "services": [],
    "dateOfEngagement": null,
    "contacts": [],
    "domesticAbuses": [],
    "substanceAbuses": [],
    "mentalHealthProblems": [],
    "hivAidsStatuses": [],
    "chronicHealthConditions": [],
    "developmentalDisabilities": [],
    "physicalDisabilities": [],
    "healthInsurances": [],
    "nonCashBenefits": [],
    "incomeSources": [],
    "housingStatus": 99,
    "statusDocumentedCode": 99,
    "monthsHomelessThisTime": 0,
    "monthsHomelessPastThreeYears": 99,
    "timesHomelessInPastThreeYears": 99,
    "continuouslyHomelessOneYear": 99,
    "cocCode": "",
    "clientLocationInformationDate": 1438812702284,
    "relationshipToHoH": 99,
    "householdId": "",
    "entryDate": "2005-06-07",
    "residencePriorLengthOfStay": 99,
    "otherResidence": "",
    "residencePrior": 99,
    "disablingCondition": 99,
    "projectExit": {
    "familyReunificationCode": null,
    "earlyExpulsionReason": null,
    "earlyExitReason": null,
    "projectCompletionStatus": null,
    "otherAftercarePlanOrAction": null,
    "resourcePackage": null,
    "scheduledFollowupContacts": null,
    "furtherFollowupServices": null,
    "exitCounciling": null,
    "temporaryShelterPlacement": null,
    "permanentHousingPlacement": null,
    "assistanceMainstreamBenefits": null,
    "writtenAftercarePlan": null,
    "mentalHealthStatus": null,
    "dentalHealthStatus": null,
    "generalHealthStatus": null,
    "notEmployedReason": null,
    "employmentType": null,
    "employed": null,
    "employedInformationDate": null,
    "connectionWithSoar": null,
    "subsidyInformation": null,
    "housingAssessment": null,
    "otherDisposition": null,
    "assessmentDisposition": null,
    "otherDestination": null,
    "destinationTypeCode": null,
    "projectExitDate": null,
    "enrollmentId": null,
    "exitId": null
    },
    "personalId": "336788",
    "enrollmentId": "46521"
    },{
    "dateUpdated": "2015-05-08",
    "dateCreated": "2005-06-15",
    "addressDataQuality": 99,
    "lastPermanentZip": "",
    "lastPermanentState": "",
    "lastPermanentCity": "",
    "lastPermanentStreet": "",
    "percentAmi": 99,
    "worstHousingSituation": 99,
    "medicalAssistances": [],
    "askedOrForcedToExchangeForSex": 99,
    "countOfExchangeForSex": 99,
    "exchangeForSexPastThreeMonths": 99,
    "countOUtreachReferralApproaches": 0,
    "referralSource": 99,
    "incarceratedParentStatus": 99,
    "incarceratedParent": 99,
    "activeMilitaryParent": 99,
    "insufficientIncome": 99,
    "alcoholDrugAbuseFam": 99,
    "alcoholDrugAbuseYouth": 99,
    "abuseAndNeglectFam": 99,
    "abuseAndNeglectYouth": 99,
    "mentalDisabilityFam": 99,
    "mentalDisabilityYouth": 99,
    "physicalDisabilityFam": 99,
    "physicalDisabilityYouth": 99,
    "healthIssuesFam": 99,
    "healthIssuesYouth": 99,
    "mentalHealthIssuesFam": 99,
    "mentalHealthIssuesYouth": 99,
    "unemploymentFam": 99,
    "unemploymentYouth": 99,
    "schoolEducationalIssuesFam": 99,
    "schoolEducationalIssuesYouth": 99,
    "housingIssuesFam": 99,
    "housingIssuesYouth": 99,
    "sexualOrientationGenderIdFam": 99,
    "sexualOrientationGenderIdYouth": 99,
    "householdDynamics": 99,
    "juvenileJusticeMonths": 0,
    "juvenileJusticeYears": 99,
    "formerWardJuvenileJustice": 99,
    "childWelfareMonths": 0,
    "childWelfareYears": 99,
    "formerlyChildWelfare": 99,
    "pregnancyDueDate": 1438812702284,
    "pregnancyStatusCode": 99,
    "mentalHealthStatus": 99,
    "dentalHealthStatus": 99,
    "generalHealthStatus": 99,
    "notEmployedReason": 99,
    "employmentType": 99,
    "employed": 99,
    "employedInformationDate": 1438812702284,
    "schoolStatus": 99,
    "lastGradeCompleted": 99,
    "sexualOrientation": 99,
    "reasonNoServices": 99,
    "fysbYouth": 99,
    "dateOfBcpStatus": 1438812702284,
    "reasonNotEnrolled": 99,
    "clientEnrolledInPath": 99,
    "dateOfPathStatus": 1438812702284,
    "permanentHousingMoveDate": 1438812702284,
    "inPermanentHousing": 99,
    "residentialMoveInDate": 1438812702284,
    "referrals": [],
    "financialAssistances": [],
    "services": [],
    "dateOfEngagement": null,
    "contacts": [],
    "domesticAbuses": [],
    "substanceAbuses": [],
    "mentalHealthProblems": [],
    "hivAidsStatuses": [],
    "chronicHealthConditions": [],
    "developmentalDisabilities": [],
    "physicalDisabilities": [],
    "healthInsurances": [],
    "nonCashBenefits": [],
    "incomeSources": [],
    "housingStatus": 99,
    "statusDocumentedCode": 99,
    "monthsHomelessThisTime": 0,
    "monthsHomelessPastThreeYears": 99,
    "timesHomelessInPastThreeYears": 99,
    "continuouslyHomelessOneYear": 99,
    "cocCode": "",
    "clientLocationInformationDate": 1438812702284,
    "relationshipToHoH": 99,
    "householdId": "",
    "entryDate": "2005-06-14",
    "residencePriorLengthOfStay": 99,
    "otherResidence": "",
    "residencePrior": 99,
    "disablingCondition": 99,
    "projectExit": {
    "familyReunificationCode": null,
    "earlyExpulsionReason": null,
    "earlyExitReason": null,
    "projectCompletionStatus": null,
    "otherAftercarePlanOrAction": null,
    "resourcePackage": null,
    "scheduledFollowupContacts": null,
    "furtherFollowupServices": null,
    "exitCounciling": null,
    "temporaryShelterPlacement": null,
    "permanentHousingPlacement": null,
    "assistanceMainstreamBenefits": null,
    "writtenAftercarePlan": null,
    "mentalHealthStatus": null,
    "dentalHealthStatus": null,
    "generalHealthStatus": null,
    "notEmployedReason": null,
    "employmentType": null,
    "employed": null,
    "employedInformationDate": null,
    "connectionWithSoar": null,
    "subsidyInformation": null,
    "housingAssessment": null,
    "otherDisposition": null,
    "assessmentDisposition": null,
    "otherDestination": null,
    "destinationTypeCode": null,
    "projectExitDate": null,
    "enrollmentId": null,
    "exitId": null
    },
    "personalId": "336825",
    "enrollmentId": "46932"
    }]```

* GET:
  - Path: `/enrollments/{enrollmentId}`
  - Method name: getClient("enrollmentId")
  - Parameters: Takes an `enrollmentId`.
  - Responses: Returns an enrollment with all subresources.
  - Example:
    - Call: `$ curl http://localhost:8080/openhmis/api/v3/enrollments/46521`
    - Response: ```{
  "dateUpdated": "2015-05-08",
  "dateCreated": "2005-06-08",
  "addressDataQuality": 99,
  "lastPermanentZip": "",
  "lastPermanentState": "",
  "lastPermanentCity": "",
  "lastPermanentStreet": "",
  "percentAmi": 99,
  "worstHousingSituation": 99,
  "medicalAssistances": [],
  "askedOrForcedToExchangeForSex": 99,
  "countOfExchangeForSex": 99,
  "exchangeForSexPastThreeMonths": 99,
  "countOUtreachReferralApproaches": 0,
  "referralSource": 99,
  "incarceratedParentStatus": 99,
  "incarceratedParent": 99,
  "activeMilitaryParent": 99,
  "insufficientIncome": 99,
  "alcoholDrugAbuseFam": 99,
  "alcoholDrugAbuseYouth": 99,
  "abuseAndNeglectFam": 99,
  "abuseAndNeglectYouth": 99,
  "mentalDisabilityFam": 99,
  "mentalDisabilityYouth": 99,
  "physicalDisabilityFam": 99,
  "physicalDisabilityYouth": 99,
  "healthIssuesFam": 99,
  "healthIssuesYouth": 99,
  "mentalHealthIssuesFam": 99,
  "mentalHealthIssuesYouth": 99,
  "unemploymentFam": 99,
  "unemploymentYouth": 99,
  "schoolEducationalIssuesFam": 99,
  "schoolEducationalIssuesYouth": 99,
  "housingIssuesFam": 99,
  "housingIssuesYouth": 99,
  "sexualOrientationGenderIdFam": 99,
  "sexualOrientationGenderIdYouth": 99,
  "householdDynamics": 99,
  "juvenileJusticeMonths": 0,
  "juvenileJusticeYears": 99,
  "formerWardJuvenileJustice": 99,
  "childWelfareMonths": 0,
  "childWelfareYears": 99,
  "formerlyChildWelfare": 99,
  "pregnancyDueDate": 1438812808842,
  "pregnancyStatusCode": 99,
  "mentalHealthStatus": 99,
  "dentalHealthStatus": 99,
  "generalHealthStatus": 99,
  "notEmployedReason": 99,
  "employmentType": 99,
  "employed": 99,
  "employedInformationDate": 1438812808842,
  "schoolStatus": 99,
  "lastGradeCompleted": 99,
  "sexualOrientation": 99,
  "reasonNoServices": 99,
  "fysbYouth": 99,
  "dateOfBcpStatus": 1438812808842,
  "reasonNotEnrolled": 99,
  "clientEnrolledInPath": 99,
  "dateOfPathStatus": 1438812808842,
  "permanentHousingMoveDate": 1438812808842,
  "inPermanentHousing": 99,
  "residentialMoveInDate": 1438812808842,
  "referrals": [],
  "financialAssistances": [],
  "services": [],
  "dateOfEngagement": null,
  "contacts": [],
  "domesticAbuses": [],
  "substanceAbuses": [],
  "mentalHealthProblems": [],
  "hivAidsStatuses": [],
  "chronicHealthConditions": [],
  "developmentalDisabilities": [],
  "physicalDisabilities": [],
  "healthInsurances": [],
  "nonCashBenefits": [],
  "incomeSources": [],
  "housingStatus": 99,
  "statusDocumentedCode": 99,
  "monthsHomelessThisTime": 0,
  "monthsHomelessPastThreeYears": 99,
  "timesHomelessInPastThreeYears": 99,
  "continuouslyHomelessOneYear": 99,
  "cocCode": "",
  "clientLocationInformationDate": 1438812808842,
  "relationshipToHoH": 99,
  "householdId": "",
  "entryDate": "2005-06-07",
  "residencePriorLengthOfStay": 99,
  "otherResidence": "",
  "residencePrior": 99,
  "disablingCondition": 99,
  "projectExit": {
    "familyReunificationCode": null,
    "earlyExpulsionReason": null,
    "earlyExitReason": null,
    "projectCompletionStatus": null,
    "otherAftercarePlanOrAction": null,
    "resourcePackage": null,
    "scheduledFollowupContacts": null,
    "furtherFollowupServices": null,
    "exitCounciling": null,
    "temporaryShelterPlacement": null,
    "permanentHousingPlacement": null,
    "assistanceMainstreamBenefits": null,
    "writtenAftercarePlan": null,
    "mentalHealthStatus": null,
    "dentalHealthStatus": null,
    "generalHealthStatus": null,
    "notEmployedReason": null,
    "employmentType": null,
    "employed": null,
    "employedInformationDate": null,
    "connectionWithSoar": null,
    "subsidyInformation": null,
    "housingAssessment": null,
    "otherDisposition": null,
    "assessmentDisposition": null,
    "otherDestination": null,
    "destinationTypeCode": null,
    "projectExitDate": null,
    "enrollmentId": null,
    "exitId": null
  },
  "personalId": "336788",
  "enrollmentId": "46521"
}```

* POST:
  - Path: `/enrollments/`
  - Method name: `createEnrollment(String data)`
  - Parameters: This takes JSON-formatted enrollment data.
  - Responses: This returns a new enrollment, JSON-formatted.
  - Example:

* PUT:
  - Path: `/enrollments/{enrollmentId}`
  - Method name: `updateEnrollment("enrollmentId")`
  - Parameters:  Takes an `enrollmentId` and data corresponding to the fields to be updated.
  - Responses: Returns a JSON-formatted string with data for the updated enrollment.
  - Example:

* DELETE:
  - Path: `/enrollments/{enrollmentId}`
  - Method name: `deleteEnrollment("enrollmentId")`
  - Parameters: Takes an `enrollmentId` for the client to be deleted.
  - Responses: Returns `true` if the enrollment was successfully deleted.
  - Example:
    - Call: `$ curl -X DELETE http://localhost:8080/openhmis/api/v3/enrollments/46521
    - Response: `true`


## Exit

* GET
  - Path: `/enrollments/{enrollmentId}/exits`
  - Method name: getExits("enrollmentId")
  - Parameters:
  - Responses:
  - Example:

* GET:
  - Path: `/enrollments/{enrollmentId}/exits/{exitId}`
  - Method name: `getExit("enrollmentId", "exitId")`
  - Parameters: enrollmentId and exitId
  - Responses: 
  - Example:

* POST:
  - Path: `/enrollments/{enrollmentId}/exits`
  - Method name: createExit(@PathParam("enrollmentId")
  - Parameters:
  - Responses:
  - Example:

* PUT:
  - Path: `/enrollments/{enrollmentId}/exits/{exitId}`
  - Method name: updateExit("enrollmentId", "exitId")
  - Parameters: enrollmentId and exitId
  - Responses: 
  - Example:

* DELETE:
  - Path: `/enrollments/{enrollmentId}/exits/{exitId}`
  - Method name: deleteExit("enrollmentId", "exitId")
  - Parameters: enrollmentId and exitId
  - Responses:
  - Example:


## Chronic Health Conditions

* GET:
  -Path: `/enrollments/{enrollmentId}/chronic-health-conditions/`
  - Method name: getChronicHealthConditions("enrollmentId")
  - Parameters:
  - Responses: 
  - Example:

* GET:
  -Path: `/enrollments/{enrollmentId}/chronic-health-conditions/{chronicHealthConditionId}`
  - Method name: getChronicHealthCondition("enrollmentId", "chronicHealthConditionId")
  - Parameters: enrollmentId and chronicHealthConditionId
  - Responses: 
  - Example:

* POST:
  - Path: `/enrollments/{enrollmentId}/chronic-health-conditions`
  - Method name: createChronicHealthCondition("enrollmentId")
  - Parameters:
  - Responses:
  - Example:

* PUT:
  - Path: `/enrollments/{enrollmentId}/chronic-health-conditions/{chronicHealthConditionId}`
  - Method name: updateChronicHealthCondition("enrollmentId", "chronicHealthConditionId")
  - Parameters: enrollmentId and chronicHealthConditionId
  - Responses: 
  - Example:

* DELETE:
  - Path: `/enrollments/{enrollmentId}/chronic-health-conditions/{chronicHealthConditionId}`
  - Method name: deleteChronicHealthCondition("enrollmentId", "chronicHealthConditionId")
  - Parameters: enrollmentId and chronicHealthConditionId
  - Responses:
  - Example:


## Contacts
* GET:
  - Path: `/enrollments/{enrollmentId}/contacts/`
  - Method name: getContacts("enrollmentId")
  - Parameters:
  - Responses: 
  - Example:

* GET:
  - Path: `/enrollments/{enrollmentId}/contacts/{contactId}`
  - Method name: getContact("enrollmentId", "contactId")
  - Parameters: enrollmentId and contactId
  - Responses: 
  - Example:

* POST:
  - Path: `/enrollments/{enrollmentId}/contacts`
  - Method name: createContact("enrollmentId")
  - Parameters:
  - Responses:
  - Example:

* PUT:
  - Path: `/enrollments/{enrollmentId}/contacts/{contactId}`
  - Method name: updateContact("enrollmentId", "contactId")
  - Parameters: enrollmentId and contactId
  - Responses: 
  - Example:

* DELETE:
  - Path: `/enrollments/{enrollmentId}/contacts/{contactId}`
  - Method name: deleteContact("enrollmentId", "contactId")
  - Parameters: enrollmentId and contactId
  - Responses:
  - Example:

## Developmental Disability 
* GET:
  - Path: `/enrollments/{enrollmentId}/developmental-disabilities/`
  - Method name: getDevelopmentalDisabilities("enrollmentId")
  - Parameters:
  - Responses: 
  - Example:

* GET:
  - Path: `/enrollments/{enrollmentId}/developmental-disabilities/{developmentalDisabilityId}`
  - Method name: getDevelopmentalDisability("enrollmentId", "developmentalDisabilityId")
  - Parameters: enrollmentId and developmentalDisabilityId
  - Responses: 
  - Example:

* POST:
  - Path: `/enrollments/{enrollmentId}/developmental-disabilities`
  - Method name: createDevelopmentalDisability("enrollmentId")
  - Parameters:
  - Responses:
  - Example:

* PUT:
  - Path: `/enrollments/{enrollmentId}/developmental-disabilities/{developmentalDisabilityId}`
  - Method name: updateDevelopmentalDisability("enrollmentId", "developmentalDisabilityId")
  - Parameters: enrollmentId and developmentalDisabilityId
  - Responses: 
  - Example:

* DELETE:
  - Path: `/enrollments/{enrollmentId}/developmental-disabilities/{developmentalDisabilityId}`
  - Method name: deleteDevelopmentalDisability("enrollmentId", "developmentalDisabilityId")
  - Parameters: enrollmentId and developmentalDisabilityId
  - Responses:
  - Example:

## Domestic Abuse

* GET:
  - Path: `/enrollments/{enrollmentId}/domestic-abuses/`
  - Method name: getDomesticAbuses("enrollmentId")
  - Parameters:
  - Responses: 
  - Example:

* GET:
  - Path: `/enrollments/{enrollmentId}/domestic-abuses/{domesticAbuseId}`
  - Method name: getDomesticAbuse("enrollmentId", "domesticAbuseId")
  - Parameters: enrollmentId and domesticAbuseId
  - Responses: 
  - Example:

* POST:
  - Path: `/enrollments/{enrollmentId}/domestic-abuses`
  - Method name: createDomesticAbuse("enrollmentId")
  - Parameters:
  - Responses:
  - Example:

* PUT:
  - Path: `/enrollments/{enrollmentId}/domestic-abuses/{domesticAbuseId}`
  - Method name: updateDomesticAbuse("enrollmentId", "domesticAbuseId")
  - Parameters: enrollmentId and domesticAbuseId
  - Responses: 
  - Example:

* DELETE:
  - Path: `/enrollments/{enrollmentId}/domestic-abuses/{domesticAbuseId}`
  - Method name: deleteDomesticAbuse("enrollmentId", "domesticAbuseId")
  - Parameters: enrollmentId and domesticAbuseId
  - Responses:
  - Example:
