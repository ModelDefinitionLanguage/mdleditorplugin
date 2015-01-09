<!DOCTYPE html>
<html lang="en">
<head>
    <title>${appName} - ${appVersion}</title>
    <link rel="stylesheet" href="default.css">
</head>
<body>
    <h1>Application: ${appName}</h1>
    <h1>Version: ${appVersion}</h2>
    <#if supportUrl?? && supportUrl?length &gt; 0 >
        <h2>Support URL: ${supportUrl!""}</h2>
    </#if>
    <p>This page provides description of Converter Toolbox Service REST API. The content is organised in two sections:
    <ol>
    <li>Relations - description of link relations used by the service</li>
    <li>Data Types - description of data types returned by the service</li>
    </ol>.
    
    Converter Toolbox Service uses Hypermedia as the Engine of Application State (HATEOAS) RESTful application architecture. (see <a href="http://en.wikipedia.org/wiki/HATEOAS">Wikipedia</a> and <a href="http://stateless.co/hal_specification.html">HAL Specification</a> for more information).
    The chosen architecture style means that clients should make NO assumptions on service's endpoints naming conventions and should only use links attached to returned resource representations.
    </p>
    
    <div id="relations">
    <h2>Relations</h2>
    <div class="entry">
        <h3><a id="home" class="relation">home</a> Relation</h3>
        <h4>Description</h4>
        <p>The target of the link with the relation is the Converter Toolbox Service entry point. Clients should follow this link to retrieve information on the service capabilities and it's metadata.</p>
        <h4>Methods</h4>
        <p>There are the following HTTP verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>GET</h5>
            <table>
            <tr><th>Input Parameters</th><td>No input parameters are expected</td></tr>
            <tr><th>Output MediaType</th><td>application/hal+json</td></tr>
            <tr><th>Output Data Type</th><td><a href="#service_descriptor_type">Service Descriptor</a></td></tr>
            <tr><th>Notes</th><td>The returned structure represents a service status with its capabilities. Clients are expected to use the information and links returned with this structure to integrate with the service.</td></tr>
            <tr><th>Example</th><td><div class="example">
                <p>The snippet below presents an example Service Descriptor Resource returned by the endpoint with 'home' relation. The clients are provided with the information on:
                <ul>
                    <li>name of the service</li>
                    <li>version of the service</li>
                    <li>list of capabilities, which clients should query for supported conversions.</li>
                    <li>list of links representing available transitions</li>
                </ul>
                The client receiving the response below could for example as a next step in the workflow submit a <a href="#conversion_type">Conversion</a> object to the URL annotated with "ddmore:submit" relation together with an archive containing inputs of the conversion.
                The 'from' and 'to' attributes of the submitted <a href="#conversion_type">Conversion</a> should match 'source' and 'target' of one of the capabilities listed by the Service Descriptor.
                </p>
                <pre class="example">
{
   "content":{
      "name":"Converter Toolbox Service",
      "version":"1.0.0",
      "capabilities":[
         {
            "source":{
               "language":"MDL",
               "version":{
                  "major":5,
                  "minor":0,
                  "patch":8,
                  "qualifier":"succeeding"
               }
            },
            "target":[
               {
                  "language":"NMTRAN",
                  "version":{
                     "major":7,
                     "minor":2,
                     "patch":0,
                     "qualifier":"succeeding"
                  }
               }
            ]
         },
         {
            "source":{
               "language":"MDL",
               "version":{
                  "major":5,
                  "minor":0,
                  "patch":8,
                  "qualifier":"failing"
               }
            },
            "target":[
               {
                  "language":"NMTRAN",
                  "version":{
                     "major":7,
                     "minor":2,
                     "patch":0,
                     "qualifier":"failing"
                  }
               }
            ]
         }
      ]
   },
   "_links":{
      "self":{
         "href":"http://localhost:9020/"
      },
      "ddmore:conversions":{
         "href":"http://localhost:9020/conversion"
      },
      "ddmore:submit":{
         "href":"http://localhost:9020/conversion"
      },
      "curies":[
         {
            "href":"http://localhost:9020/relations{#rel}",
            "name":"ddmore",
            "templated":true
         }
      ]
   }
}
                </pre>
            </div></td></tr>
            </table>
            </div>
            <div class="note">No other verbs are supported for resources with the 'home' relation.</div>
        </div>
    </div>
    <div class="entry">
        <h3><a id="result" class="relation">result</a></h3>
        <h4>Description</h4>
        <p>Clients should follow this link to retrieve results of completed <a href="#conversion_type">Conversion</a>.</p>
        <h4>Methods</h4>
        <p>There are the following HTTP verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>GET</h5>
            <table><tr>
            <th>Input Parameters</th><td>No input parameters are expected</td></tr>
            <tr><th>Output MediaType</th><td>application/octetstream</td></tr>
            <tr><th>Output Data Type</th><td>ZIP archive</td></tr>
            <tr><th>Notes</th><td>The returned file format is a ZIP archive.</td></tr>
            </table>
            </div>
            <div class="note">No other verbs are supported for resources with the home relation.</div>
        </div>
    </div>
    <div class="entry">
        <h3><a id="delete" class="relation">delete</a></h3>
        <h4>Description</h4>
        <p>Clients should follow this link to remove Conversion from the service.</p>
        <h4>Methods</h4>
        <p>There are the following HTTP verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>DELETE</h5>
            <table><tr>
            <th>Input Parameters</th><td>No input parameters are expected</td></tr>
            <tr><th>Output MediaType</th><td>Empty output is produced</td></tr>
            <tr><th>Output Data Type</th><td>Empty output is produced</td></tr>
            </table>
            </div>
            <div class="note">It should be assumed that other verbs are not supported by the target resource.</div>
        </div>
    </div>
    <div class="entry">
        <h3><a id="self" class="relation">self</a></h3>
        <h4>Description</h4>
        <p>Clients should follow this link to retrieve the most recent state of the resource.</p>
        <h4>Methods</h4>
        <p>There are the following verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>GET</h5>
            <table><tr>
            <th>Input Parameters</th><td>No input parameters are expected</td></tr>
            <tr><th>Output MediaType</th><td>same media type is returned as of the source of the link</td></tr>
            <tr><th>Output Data Type</th><td>same data type is returned as of the source of the link</td></tr>
            </table>
            </div>
            <div class="note">It should be assumed that other verbs are not supported by the target resource.</div>
        </div>
    </div>
    <div class="entry">
        <h3><a id="submit" class="relation">submit</a></h3>
        <h4>Description</h4>
        <p>Clients should follow this link to submit new Conversions to the service.</p>
        <h4>Methods</h4>
        <p>There are the following verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>POST</h5>
            <table><tr>
            <th>Input Parameters</th>
            <td>multipart/form-data with the following parts <ul>
            <li>conversion - application/json representation of Conversion entity with populated 'from', 'to', 'inputFileName' attributes and 'status' set to New. See <a href="#conversion_type">Conversion Type</a> for details.</li>
            <li>file - File Part being a ZIP archive holding inputs for conversion</li>
            </ul>
            </td></tr>
            <tr><th>Output MediaType</th><td>application/hal+json on successful submission</td></tr>
            <tr><th>Output Data Type</th><td><a href="#conversion_type">Conversion</a></td></tr>
            <tr><th>Example</th>
            <td><div class="example">
                <p>Here is an example input <a href="#conversion_type">Conversion</a> structure submitted to the service</p>
                <pre class="example">
{
   "id":null,
   "status":"New",
   "from":{
      "language":"MDL",
      "version":{
         "major":5,
         "minor":0,
         "patch":8,
         "qualifier":"succeeding"
      }
   },
   "to":{
      "language":"NMTRAN",
      "version":{
         "major":7,
         "minor":2,
         "patch":0,
         "qualifier":"succeeding"
      }
   },
   "inputFileName":"success-wid.txt",
   "outputFileSize":null,
   "submissionTime":null,
   "completionTime":null,
   "conversionReport":null
}
                </pre>
            
                <p>Here is an example <a href="#conversion_type">Conversion</a> structure being returned by the service:</p>
                <pre class="example">
{
   "content":{
      "id":"4b432ddd-a702-455c-9dfe-7891d150c479",
      "status":"Scheduled",
      "from":{
         "language":"MDL",
         "version":{
            "major":5,
            "minor":0,
            "patch":8,
            "qualifier":"succeeding"
         }
      },
      "to":{
         "language":"NMTRAN",
         "version":{
            "major":7,
            "minor":2,
            "patch":0,
            "qualifier":"succeeding"
         }
      },
      "inputFileName":"success-wid.txt",
      "outputFileSize":null,
      "submissionTime":1420803473318,
      "completionTime":null,
      "conversionReport":null
   },
   "_links":{
      "self":{
         "href":"http://localhost:9020/conversion/4b432ddd-a702-455c-9dfe-7891d150c479"
      }
   }
}
                </pre>
                <div class="note>Mind that the returned <a href="#conversion_type">Conversion</a> is wrapped in HAL Resource</div>
            </div></td></tr>
            </table>
            </div>
            <div class="note">It should be assumed that other verbs are not supported by the target resource.</div>
        </div>
    </div>
    <div class="entry">
        <h3><a id="conversions" class="relation">conversions</a></h3>
        <h4>Description</h4>
        <p>Clients should follow this link to retrieve the list of currently processed conversions.</p>
        <h4>Methods</h4>
        <p>There are the following HTTP verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>GET</h5>
            <table><tr>
            <th>Input Parameters</th><td>No input parameters are expected</td></tr>
            <tr><th>Output MediaType</th><td>application/hal+json</td></tr>
            <tr><th>Output Data Type</th><td>Collection of <a href="#conversion_type">Conversion</a> resources.</td></tr>
            </table>
            </div>
            <div class="note">It should be assumed that other verbs are not supported by the target resource.</div>
        </div>
    </div>
    <div class="entry">
        <h3><a id="support" class="relation">support</a></h3>
        <h4>Description</h4>
        <p>Clients should follow this link to submit issues to service support team.</p>
        <h4>Methods</h4>
        <p>There are the following HTTP verbs supported by the target resource.<p>
        <div class="methods_list">
            <div class="entry">
            <h5>GET</h5>
            <table><tr>
            <th>Input Parameters</th><td>No input parameters are expected</td></tr>
            <tr><th>Output MediaType</th><td>Dependent on the third party system</td></tr>
            <tr><th>Output Data Type</th><td>Dependent on the third party system</td></tr>
            </table>
            </div>
            <div class="note">It should be assumed that other verbs are not supported by the target resource.</div>
        </div>
    </div>
    </div>
    
    
    
    
    <div id="datatypes">
    <h2>Data Types</h2>
        <p>Converter Toolbox Service uses HAL MediaType (See <a href="http://stateless.co/hal_specification.html">HAL Specification</a> for details).
        This means that the returned representations of the resources are enclosed in a wrapper object containing metadata providing information on available transitions.
        </p>
        <div class="entry">
            <h3><a id="service_descriptor_type">Service Descriptor</a></h3>
            <table>
                <tr><th>Element</th><th>Description</th></tr>
                <tr><td>name</td><td>service name</td></tr>
                <tr><td>version</td><td>service version</td></tr>
                <tr><td>capabilities</td><td>List of <a href="#conversion_capability_type">Conversion Capabilities</a> (available conversions)</td></tr>
            </table>
        </div>
        <div class="entry">
            <h3><a id="conversion_capability_type">Conversion Capability</a></h3>
            <table>
                <tr><th>Element</th><th>Description</th></tr>
                <tr><td>from</td><td>Language Version from which the conversions are available</td></tr>
                <tr><td>to</td><td>Collection of <a href="#language_version_type">Language Version</a> elements to which a given conversion is supported</td></tr>
            </table>
        </div>
        <div class="entry">
            <h3><a id="language_version_type">Language Version</a></h3>
            <table>
                <tr><th>Element</th><th>Description</th></tr>
                <tr><td>language</td><td>language name</td></tr>
                <tr><td>version</td><td>language version</td></tr>
            </table>
        </div>
        <div class="entry">
            <h3><a id="conversion_type">Conversion</a></h3>
            <table>
                <tr><th>Element</th><th>Description</th><th>Notes</th></tr>
                <tr><td>id</td><td>A unique identifier of the conversion.</td><td>This is generated by the service.</td></tr>
                <tr><td>from</td><td><a href="#language_version_type">Language Version</a> from which inputs should be converted.</td><td>Both 'from' and 'to' should match available <a href="#conversion_capability_type">Conversion Capability</a></td></tr>
                <tr><td>to</td><td><a href="#language_version_type">Language Version</a> to which inputs should be converted.</td><td>Both 'from' and 'to' should match available <a href="#conversion_capability_type">Conversion Capability</a></td></tr>
                <tr><td>status</td><td>Conversion status (initial = New, Scheduled, Running, Completed)</td><td>Clients have to submit a Conversion with status 'New'.</td></tr>
                <tr><td>inputFileName</td><td>Name of the file in the input archive being an entry point for the converter.</td><td>The file must exist in the input archive submitted with the Conversion.</td></tr>
                <tr><td>outputFileSize</td><td>The size of the result archive.</td><td>Convenience methods for the clients to estimate time required for downloading results. Only available when conversion has been completed.</td></tr>
                <tr><td>submissionTime</td><td>When the conversion has been received by the service.</td><td>Populated by the service</td></tr>
                <tr><td>completionTime</td><td>When the conversion has been completed.</td><td>Populated by the service when the conversion is completed.</td></tr>
                <tr><td>conversionReport</td><td>A <a href="#conversion_report_type">Conversion Report</a> produced by the converter.</td><td>Populated by the service, only available when the conversion has been completed.</td></tr>
            </table>
            <div class="example">
                Examples of Conversions at different stages of execution
                
                <h4>Submitted Conversion
                <pre class="example">
{
   "id":null,
   "status":"New",
   "from":{
      "language":"MDL",
      "version":{
         "major":5,
         "minor":0,
         "patch":8,
         "qualifier":"succeeding"
      }
   },
   "to":{
      "language":"NMTRAN",
      "version":{
         "major":7,
         "minor":2,
         "patch":0,
         "qualifier":"succeeding"
      }
   },
   "inputFileName":"success-wid.txt",
   "outputFileSize":null,
   "submissionTime":null,
   "completionTime":null,
   "conversionReport":null
}
                </pre>
                
                <h4>Scheduled Conversion HAL Resource</h4>
                <pre class="example">
{
   "content":{
      "id":"b89a0cd1-b71f-441e-b317-6dd6528a05ea",
      "status":"Scheduled",
      "from":{
         "language":"MDL",
         "version":{
            "major":5,
            "minor":0,
            "patch":8,
            "qualifier":"succeeding"
         }
      },
      "to":{
         "language":"NMTRAN",
         "version":{
            "major":7,
            "minor":2,
            "patch":0,
            "qualifier":"succeeding"
         }
      },
      "inputFileName":"success-wid.txt",
      "outputFileSize":null,
      "submissionTime":1420802995405,
      "completionTime":null,
      "conversionReport":null
   },
   "_links":{
      "self":{
         "href":"http://localhost:9020/conversion/b89a0cd1-b71f-441e-b317-6dd6528a05ea"
      }
   }
}
                </pre>
                
                
                <h4>Running Conversion HAL Resource</h4>
                <pre class="example">
{
   "content":{
      "id":"b89a0cd1-b71f-441e-b317-6dd6528a05ea",
      "status":"Running",
      "from":{
         "language":"MDL",
         "version":{
            "major":5,
            "minor":0,
            "patch":8,
            "qualifier":"succeeding"
         }
      },
      "to":{
         "language":"NMTRAN",
         "version":{
            "major":7,
            "minor":2,
            "patch":0,
            "qualifier":"succeeding"
         }
      },
      "inputFileName":"success-wid.txt",
      "outputFileSize":null,
      "submissionTime":1420802995405,
      "completionTime":null,
      "conversionReport":null
   },
   "_links":{
      "self":{
         "href":"http://localhost:9020/conversion/b89a0cd1-b71f-441e-b317-6dd6528a05ea"
      }
   }
}
                </pre>
            
                <h4>Completed Conversion HAL Resource</h4>
                <p>
                Here is an example Conversion structure being returned by the service:
                </p>
                <pre class="example">
{
   "content":{
      "id":"b89a0cd1-b71f-441e-b317-6dd6528a05ea",
      "status":"Completed",
      "from":{
         "language":"MDL",
         "version":{
            "major":5,
            "minor":0,
            "patch":8,
            "qualifier":"succeeding"
         }
      },
      "to":{
         "language":"NMTRAN",
         "version":{
            "major":7,
            "minor":2,
            "patch":0,
            "qualifier":"succeeding"
         }
      },
      "inputFileName":"success-wid.txt",
      "outputFileSize":251,
      "submissionTime":1420802995405,
      "completionTime":1420802995469,
      "conversionReport":{
         "returnCode":"SUCCESS",
         "details":[
            {
               "info":{
                  "warning2":"warning2",
                  "warning1":"warning1"
               },
               "message":"WARNING message",
               "serverity":"WARNING"
            },
            {
               "info":{
                  "info1":"info1",
                  "info2":"info2"
               },
               "message":"INFO message",
               "serverity":"INFO"
            },
            {
               "info":{
                  "debug1":"debug1",
                  "debug2":"debug2"
               },
               "message":"DEBUG message",
               "serverity":"DEBUG"
            }
         ]
      }
   },
   "_links":{
      "self":{
         "href":"http://localhost:9020/conversion/b89a0cd1-b71f-441e-b317-6dd6528a05ea"
      },
      "ddmore:result":{
         "href":"http://localhost:9020/conversion/b89a0cd1-b71f-441e-b317-6dd6528a05ea/result"
      },
      "ddmore:delete":{
         "href":"http://localhost:9020/conversion/b89a0cd1-b71f-441e-b317-6dd6528a05ea"
      },
      "curies":[
         {
            "href":"http://localhost:9020/relations{#rel}",
            "name":"ddmore",
            "templated":true
         }
      ]
   }
}
                </pre>
            </div>
        </div>
        <div class="entry">
            <h3><a id="conversion_report_type">Conversion Report</a></h3>
            <table>
                <tr><th>Element</th><th>Description</th></tr>
                <tr><td>code</td><td>Outcome of the conversion (SUCCESS, FAILURE)</td></tr>
                <tr><td>details</td><td>List of <a href="#conversion_detail_type">conversion detail</a> messages</td></tr>
            </table>
        </div>
        <div class="entry">
            <h3><a id="conversion_detail_type">Conversion Detail</a></h3>
            <table>
                <tr><th>Element</th><th>Description</th></tr>
                <tr><td>severity</td><td>One of ERROR, WARNING, INFO, DEBUG</td></tr>
                <tr><td>message</td><td>Human readable message</td></tr>
                <tr><td>info</td><td>key-value list of detailed information associated with the message</td></tr>
            </table>
        </div>
    </div>
    
</body>
</html>