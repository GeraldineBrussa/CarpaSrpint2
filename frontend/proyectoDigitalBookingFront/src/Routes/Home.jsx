import React from "react";
import Buscador from "../Components/Buscador";
import Categorias from "../Components/Categorias";
import Recomendados from "../Components/Recomendados";

const Home = () => {
  return (
    <div>
      <Buscador />
      <Categorias />
      <Recomendados />
    </div>
  );
};

export default Home;
