# check-versions-service

Versions checker service that provides REST API to compare two version numbers.
- Java 8
- Spring boot 2.2.4

Provides the POST /check-versions method that takes two version numbers and returns message in response to client.
Future improvements:
- adding the GET method for comparison versions
- adding Internationalization Support 
- adding integration with in-memory database to use it as cache for results to improve perfomance (optional)

**Check versions**
----
  Returns JSON result of comparison to version numbers
  
* **URL**

  /check-versions

* **Method:**
  
  `POST`
 
*  **URL Params**

   None

* **Data Params**

   **Required:**
 
   `version1=[String]`
  
   `version2=[String]`

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{"result":"2.0 is \"after\" 1.0.1"}`
 
* **Error Response:**

  * **Code:** 400 Bad Request <br />
    **Content:** `{ "version1": "Version number should contain only digits or groups of digits separated by periods",
                    "timestamp": "2020-02-07T19:36:42.694+0000",
                     "status": 400}`



