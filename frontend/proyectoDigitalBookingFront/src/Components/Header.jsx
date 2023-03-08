import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { useGlobalStates } from "../Context/GlobalContext";
import { routes } from "../Routes/routes";

const Header = () => {
  const { providerValue } = useGlobalStates();
  const { log, setlog } = providerValue;
  return (
    <nav>
      <Link to={routes.home}>
        <img src="/img/logoHeader.png" />
      </Link>
      {log === false ? (
        <div>
          <Link to={routes.registro}>
            <button>Crear Cuenta</button>
          </Link>
          <Link to={routes.login}>
            <button>Iniciar Sesión</button>
          </Link>
        </div>
      ) : (
        <div>
          <span>NU</span>
          <p>
            Hola, <span> Nombre de Usuario </span>
          </p>
          <button>X</button>
        </div>
      )}
    </nav>
  );
};
export default Header;
