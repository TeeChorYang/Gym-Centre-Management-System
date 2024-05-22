<%-- 
    Document   : staff-information
    Created on : May 10, 2024, 8:41:45 PM
    Author     : TheOne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="models.AgcStaff"%> 

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
    <body class="h-full">
      <div class="min-h-full">
        <nav class="bg-gray-800">
          <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
            <div class="flex h-16 items-center justify-between">
              <div class="flex items-center">
                <div class="flex-shrink-0">
                    <a href="StaffHome">
                        <h1 class="text-3xl font-bold tracking-tight text-sky-500 ">APU Gym Centre</h1>
                    </a>
                </div>
                <div class="hidden md:block">
                  <div class="ml-10 flex items-baseline space-x-4">
                    <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" -->
                    <a href="StaffHome" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Dashboard</a>
                    <a href="StaffInformation" class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium" aria-current="page">Staff</a>
                    <a href="StaffTrainerInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Trainers</a>
                    <a href="StaffCustomerInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Customers</a>
                    <a href="StaffClassInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Classes</a>
                    <a href="StaffPaymentInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Payments</a>
                  </div>
                </div>
              </div>
            <div class="dropdown">
                <button type="button" class="dropbtn relative flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
                    <span class="absolute -inset-1.5"></span>
                    <span class="sr-only">Open user menu</span>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-8 w-8 rounded-full text-white">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                  </svg>
                </button>
                <div class="dropdown-content">
                  <a href="StaffProfile" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-0">Your Profile</a>
                  <a href="Logout" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-2">Sign out</a>
                </div> 
            </div>
            </div>
          </div>
        </nav>
        
        <header class="bg-white shadow">
          <div class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
            <h1 class="text-3xl font-bold tracking-tight text-gray-900">Staff's Information</h1>
          </div>
        </header>
        
        <main>
          <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
            <div class="flex flex-col">
              <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
                <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
                    <div class="bg-white px-4 py-5 border-b border-gray-200 sm:px-6">
                      <div class="-ml-4 -mt-2 flex items-center justify-between flex-wrap sm:flex-nowrap">
                        <div class="ml-4 mt-2">
                            <form action="" method="GET">
                              <input name="search" class="text-lg leading-6 font-medium text-gray-900 rounded-md 
                                     border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 
                                     focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6" placeholder="Search..." />
                              <input type="submit" value="Search" class="relative inline-flex items-center px-4 py-1.5 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500" />
                            </form>
                         </div>
                        <div class="ml-4 mt-2 flex-shrink-0">
                          <a href="StaffCreate" class="relative inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium 
                             rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Create new staff</a>
                        </div>
                      </div>
                    </div>
                  <div class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg">
                    <table class="min-w-full divide-y divide-gray-200">
                      <thead class="bg-gray-50">
                        <tr>
                          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
                          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Name</th>
                          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Phone</th>
                          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Admin</th>
                          <th scope="col" class="relative px-6 py-3">
                            <span class="sr-only">Edit</span>
                          </th>
                          <th scope="col" class="relative px-6 py-3">
                            <span class="sr-only">Delete</span>
                          </th>
                        </tr>
                      </thead>
                      <tbody class="divide-y divide-gray-200">
                        <% 
                           List<AgcStaff> staffList = (List<AgcStaff>) request.getAttribute("staffList");
                            AgcStaff currentUser = (AgcStaff) request.getSession().getAttribute("LoginUser");
                            for (AgcStaff staff : staffList) {
                        %>
                        <tr class="bg-white">
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= staff.getId() %></td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= staff.getName() %></td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= staff.getEmail() %></td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= staff.getPhone() %></td>
                            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><%= staff.getIs_admin() %></td>
                            <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                <% if (!staff.getId().equals(currentUser.getId())) { %>
                                    <a href="StaffEdit?id=<%= staff.getId() %>" class="text-indigo-600 hover:text-indigo-900">Edit</a>
                                <% } %>
                            </td>
                            <td class="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                <% if (!staff.getId().equals(currentUser.getId())) { %>
                                    <a href="#" onclick="confirmDelete('<%= staff.getId() %>')" class="text-red-600 hover:text-red-900">Delete</a>
                                <% } %>
                            </td>
                        </tr>
                        <% 
                            }
                        %>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </main>
        <script>
            function confirmDelete(id) {
                if (confirm('Are you sure you want to delete this staff?')) {
                    window.location.href = 'StaffDelete?id=' + id;
                }
            };
            window.onload = function() {
                const urlParams = new URLSearchParams(window.location.search);
                const error = urlParams.get('error');
                if (error) {
                    alert(error);
                }
            }
        </script>
 
    </body>
</html>
