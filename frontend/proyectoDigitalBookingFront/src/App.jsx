import { Routes, Route } from "react-router-dom";
import Header from "./Components/Header";
import "./App.css";
import Footer from "./Components/Footer";
import { routes } from "./Routes/routes";
import Home from "./Routes/Home";
import NotFound from "./Routes/NotFound";
import Login from "./Routes/Login";
import Registro from "./Routes/Registro";

function App() {
  return (
    <div className="App">
      <Header />
      <Routes>
        <Route path={routes.home} element={<Home />} />
        <Route path={routes.login} element={<Login />} />
        <Route path={routes.registro} element={<Registro />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
