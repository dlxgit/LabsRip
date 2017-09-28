<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <form method="post" action="MyFirstServlet">
      Name: <input type="text" name="bookName">
      Author: <input type="text" name="bookAuthor">
      Year: <input type="text" name="bookYear">
      Genre: <input type="text" name="bookGenre">
      <button>Add</button>
    </form>
    <form action="/MyFirstServlet">
      <button>Show all books</button>
    </form>
  </body>
</html>
