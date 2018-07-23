<%-- 
    Document   : header
    Created on : Jul 11, 2018, 11:11:54 PM
    Author     : Administrator
--%>

<div class="cover"></div>
<div class="header" id="myheader">
    <div class="main-header">
        <div class="logo">
            <!--<a href="HomeController">-->
                <b>Book</b>Share
            <!--</a>-->
        </div> 
        <div class="menu">
            <ul>
                <li>
                    <a href="HomeController">Home</a>
                </li>
                <li>
                    <a href="#">Manage Book</a>
                </li>
                <li>
                    <a href="TradingController">Trading</a>
                </li>
            </ul>
        </div>
        <div class="search">
            <div class="search-container">
                <form action="SearchBookController">
                    <input type="text" placeholder="Search.." name="query" autocomplete="off" value="${param.query}">
                    <button type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </form>
            </div>
        </div>
        <div class="notifications">
            <button>
                <i class="fas fa-bell"></i>
            </button>
        </div>
        <div class="dropdown">
            <button>
                <i class="fas fa-user"></i>
            </button>
            <div class="dropdown-content">
                <a href="UpdateProfileController">Profile</a>
                <a href="#">Settings</a>
                <a href="LogoutController">Logout</a>
            </div>
        </div>
    </div>
</div>
