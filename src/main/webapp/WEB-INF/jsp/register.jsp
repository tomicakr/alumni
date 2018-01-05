<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <%@ page contentType="text/html; charset=UTF-8" %>
  <title>Register</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.0.8/semantic.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.0.8/semantic.min.js"></script>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/register.css">
</head>

<body>
  <h1>92440124851</h1>
  <main class="ui middle aligned center aligned grid">
    <div class="column">
      <h1 class="ui image header">
                Izradite svoj račun
            </h1>
      <form class="ui large form segment stacked">
        <div class="two fields">
          <div class="field">
            <input type="text" name="first-name" placeholder="Ime">
          </div>
          <div class="field">
            <input type="text" name="last-name" id="last-name" placeholder="Prezime">
          </div>
        </div>
        <div class="field">
          <input type="text" name="oib" id="oib" placeholder="OIB">
        </div>
        <div class="field">
          <input type="tel" name="phone" id="phone" placeholder="Broj telefona">
        </div>
        <div class="field">
          <input type="email" name="email" id="email" placeholder="Adresa elektroničke pošte">
        </div>
        <div class="field">
          <input type="text" name="address" id="address" placeholder="Adresa stavnovanja">
        </div>
        <div class="field">
          <input type="password" name="password" id="password" placeholder="Lozinka">
        </div>
        <div class="field">
          <input type="password" name="password2" id="password2" placeholder="Ponovite lozinku">
        </div>
        <i id="pass-match" class="fa large" aria-hidden="true"></i>
        <div class="ui fluid huge darkred submit button" style="width: 100%">Registriraj se!</div>
      </form>
    </div>
  </main>
  <script src="${pageContext.request.contextPath}/scripts/register.js">
  </script>
</body>

</html>