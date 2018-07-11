<%-- 
    Document   : content-left
    Created on : Jul 11, 2018, 11:12:52 PM
    Author     : Administrator
--%>

<div class="content-left">
    <section class="section-left">
        <h3>CURRENTLY READING</h3>
        <div class="section-left-content">
            <img src="img/book-icon.svg" alt="book-icon">
            <p>What books do you want
                <br> to find?</p>
        </div>
        <div class="search-container">
            <form action="/action_page.php">
                <input type="text" placeholder="Search books" name="search" autocomplete="off">
                <button type="submit">
                    <i class="fa fa-search"></i>
                </button>
            </form>
        </div>
    </section>
    <section class="section-left">
        <h3>YOUR BOOK UPLOAD</h3>
        <div class="section-left-content">
            <div class="book-color"></div>
            <div class="book-color-content">
                <h2>0</h2>
                <span>books upload</span>
                <br>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span>
                <br>
                <a href="#">Upload book now</a>
            </div>
        </div>
    </section>
    <section class="section-left">
        <h3>#CATEGORY</h3>
        <div class="section-left-content">
            <ul>
                <a href="#">
                    <li>#Self-help books</li>
                </a>
                <a href="#">
                    <li>#Autobiographies</li>
                </a>
                <a href="#">
                    <li>#Comic books</li>
                </a>
                <a href="#">
                    <li>#Technical books?</li>
                </a>
                <a href="#">
                    <li>#Children's books?</li>
                </a>
            </ul>
        </div>
    </section>
</div>