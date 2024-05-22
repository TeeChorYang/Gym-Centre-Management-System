<%-- 
    Document   : customer-class
    Created on : May 20, 2024, 11:37:14 PM
    Author     : TheOne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="models.AgcClass"%>
<%@page import="models.AgcPayment"%>
<%@page import="models.AgcPaymentFacade"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>APU Gym Centre</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp,container-queries"></script>
         <script>
            tailwind.config = {
              theme: {
                extend: {
                  colors: {
                    clifford: '#da373d',
                  }
                }
              }
            }
          </script>
          <style>
            .dropdown {
              position: relative;
              display: inline-block;
            }

            .dropdown-content {
              display: none;
              position: absolute;
              background-color: #f9f9f9;
              min-width: 160px;
              box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
              z-index: 1;
            }

            .dropdown-content a {
              color: black;
              padding: 12px 16px;
              text-decoration: none;
              display: block;
            }

            .dropdown-content a:hover {background-color: #f1f1f1;}

            .dropdown:hover .dropdown-content {
              display: block;
            }
        </style>
    </head>
    <body>
        <header class="bg-white shadow">
          <div class="max-w-7xl mx-auto px-2 sm:px-4 lg:divide-y lg:divide-gray-200 lg:px-8">
            <div class="relative h-16 flex justify-between">
              <div class="relative z-10 px-2 flex lg:px-0">
                <% if(session.getAttribute("message") != null) { %>
                    <script type="text/javascript">
                        alert('<%= session.getAttribute("message") %>');
                    </script>
                    <% session.removeAttribute("message"); %>
                <% } %>
                <div class="flex-shrink-0 flex items-center">
                    <a href="CustomerHome">
                        <h1 class="text-3xl font-bold tracking-tight text-sky-500 ">APU Gym Centre</h1>
                    </a>
                </div>
              </div>
              
              
              <div class="hidden lg:relative lg:z-10 lg:ml-4 lg:flex lg:items-center">
                <div class="flex-shrink-0 relative ml-4">
                    
                    <div class="dropdown">
                        <button type="button" class="dropbtn relative flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
                            <span class="absolute -inset-1.5"></span>
                            <span class="sr-only">Open user menu</span>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-8 w-8 rounded-full text-white">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                          </svg>
                        </button>
                        <div class="dropdown-content">
                          <a href="CustomerProfile" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-0">Your Profile</a>
                          <a href="Logout" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-2">Sign out</a>
                        </div> 
                    </div>
                </div>
              </div>
            </div>
            <nav class="hidden lg:py-2 lg:flex lg:space-x-8" aria-label="Global">
              <a href="CustomerHome" class="text-gray-900 hover:bg-gray-50 hover:text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium"> Dashboard </a>

              <a href="CustomerClass" class="bg-gray-100 text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium" aria-current="page"> Classes  </a>
              
              <a href="CustomerClassJoined" class="text-gray-900 hover:bg-gray-50 hover:text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium"> My Classes</a>
            </nav>
          </div>
        </header>
        
        <main>
          <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
            <div class="bg-white">
                <div class="space-y-12">
                  <div class="flex justify-between items-center border-b border-gray-200">
                    <h2 class="text-3xl font-extrabold tracking-tight sm:text-4xl">Class List</h2>
                    <div class="bg-white px-4 py-5 sm:px-6">
                      <div class="-ml-4 -mt-2 flex items-center justify-end flex-wrap sm:flex-nowrap">
                        <div class="ml-4 mt-2">
                          <form action="" method="GET" class="flex items-center">
                            <input name="search" class="text-lg leading-6 font-medium text-gray-900 rounded-md 
                                   border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 
                                   focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" placeholder="Search..." />
                            <input type="submit" value="Search" class="relative inline-flex items-center px-4 py-1.5 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-sky-600 hover:bg-sky-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" />
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>
                  <ul role="list" class="space-y-12 lg:grid lg:grid-cols-2 lg:items-start lg:gap-x-8 lg:gap-y-12 lg:space-y-0">
                        <% 
                            List<AgcClass> classList = null;
                            if (request.getAttribute("classList") != null) {
                                classList = (List<AgcClass>) request.getAttribute("classList");
                            }
                            if (classList != null) {
                                for (AgcClass agcClass : classList) {
                        %>
                    <li>
                      <div class="space-y-4 sm:grid sm:grid-cols-3 sm:gap-6 sm:space-y-0 lg:gap-8">
                        
                        <div class="sm:col-span-2">
                          <div class="space-y-4">
                            <div class="text-lg leading-6 font-medium space-y-1">
                              <h3><%= agcClass.getName() %></h3>
                              <p class="text-indigo-400">$ <%= agcClass.getPrice() %></p>
                            </div>
                            <div class="text-lg">
                              <p class="text-gray-500"><%= agcClass.getDescription() %></p>
                            </div>
                            <div class="flex justify-end space-x-5">
                                <% List<String> paidClassIds = (List<String>) request.getAttribute("paidClassIds"); %>
                                <% List<String> rejectedClassIds = (List<String>) request.getAttribute("rejectedClassIds"); %>
                                <% if (!paidClassIds.contains(agcClass.getId()) || rejectedClassIds.contains(agcClass.getId())) { %>
                                    <a href="CustomerClassApply?classId=<%= agcClass.getId() %>" class="text-black hover:text-black bg-sky-600 text-white px-4 py-2 rounded">Join Class</a>
                                <% } %>
                            </div>
                          </div>
                        </div>
                      </div>
                    </li>
                    <% 
                            }
                        }
                    %>
                  </ul>
                </div>
            </div>

          </div>
        </main>

    </body>
</html>

