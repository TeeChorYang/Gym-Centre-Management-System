<%-- 
    Document   : trainer-customer-feedback
    Created on : May 19, 2024, 5:49:29 PM
    Author     : TheOne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="models.AgcCustomer"%>
<%@page import="models.AgcFeedback"%>
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
                <div class="flex-shrink-0 flex items-center">
                    <a href="TrainerHome">
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
                          <a href="TrainerProfile" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-0">Your Profile</a>
                          <a href="Logout" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-2">Sign out</a>
                        </div> 
                    </div>
                </div>
              </div>
            </div>
            <nav class="hidden lg:py-2 lg:flex lg:space-x-8" aria-label="Global">
              <a href="TrainerHome" class="text-gray-900 hover:bg-gray-50 hover:text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium"> Dashboard  </a>
              <a href="TrainerClass" class="bg-gray-100 text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium" aria-current="page"> Classes </a>
            </nav>
          </div>
        </header>
        <main>
            <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
                <div class="bg-white px-4 py-5 border-b border-gray-200 sm:px-6">
                    <div class="-ml-4 -mt-2 flex items-center justify-between flex-wrap sm:flex-nowrap">
                        <div class="ml-4 mt-2">
                            <h2 class="text-base font-semibold leading-7 text-gray-900">Customer Name: <%= request.getAttribute("customerName") %></h2>
                            <h2 class="text-base font-semibold leading-7 text-gray-900">Class Name: <%= request.getAttribute("className") %></h2>
                        </div>
                        <div class="ml-4 mt-2 flex-shrink-0">
                            <a href="TrainerFeedbackCreate?id=<%= request.getAttribute("customerId") %>&classId=<%= request.getAttribute("classId") %>" class="relative inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium 
                            rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Add feedback</a>
                        </div>
                    </div>
                </div>
                <ul role="list" class="divide-y divide-gray-200">
                    <% List<AgcFeedback> feedbacks = (List<AgcFeedback>) request.getAttribute("feedbacks");
                       for (AgcFeedback feedback : feedbacks) { %>
                        <li class="relative bg-white py-5 px-4 hover:bg-gray-50 focus-within:ring-2 focus-within:ring-inset focus-within:ring-indigo-600">
                            <div class="flex justify-between space-x-3">
                                <div class="min-w-0 flex-1">
                                    <a href="#" class="block focus:outline-none">
                                        <span class="absolute inset-0" aria-hidden="true"></span>
                                        <p class="text-sm font-medium text-gray-900 truncate">
                                            <time datetime="<%= feedback.getFeedbackDate() %>" class="flex-shrink-0 whitespace-nowrap text-sm text-gray-500"><%= feedback.getFeedbackDate() %></time>
                                        </p>
                                    </a>
                                </div>
                            </div>
                            <div class="mt-1">
                                <p class="line-clamp-2 text-sm text-gray-600"><%= feedback.getFeedback() %></p>
                            </div>
                        </li>
                    <% } %>
                </ul>
            </div>
        </main>
    </body>
</html>
