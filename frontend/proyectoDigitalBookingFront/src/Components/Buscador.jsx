import { useRef, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale } from "react-datepicker";
import es from "date-fns/locale/es";
import ciudades from "../Utils/ciudades.json";

registerLocale("es", es);

const Buscador = () => {
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  const onChangeCalendar = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };

  const calendarioRef = useRef(null);

  const handleAplicar = () => {
    calendarioRef.current.setOpen(false);
  };

  return (
    <>
      <h1>
        <p>Busca ofertas en hoteles, casas y mucho más</p>
      </h1>
      <fieldset>
        <select placeholder="¿A dónde vamos?">
          <option hidden>¿A dónde vamos?</option>;
          {ciudades.map((ciudad) => (
            <option value={ciudad.id} key={ciudad.id}>
              {ciudad.nombre}
              {"  -  "}
              {ciudad.pais}
            </option>
          ))}
        </select>
        <DatePicker
          placeholderText="Check in - Check out"
          calendarStartDay={0}
          useWeekdaysShort
          onChange={onChangeCalendar}
          minDate={new Date()}
          startDate={startDate}
          endDate={endDate}
          selectsRange
          monthsShown={2}
          isClearable={true}
          shouldCloseOnSelect={false}
          fixedHeight
          dateFormat="dd/MM/yyyy"
          locale={es}
          form="buscador-form"
          ref={calendarioRef}
        >
          <button onClick={handleAplicar}>Aplicar</button>
        </DatePicker>
        <form id="buscador-form" className="" action="" method="GET">
          <button>Buscar</button>
        </form>
      </fieldset>
    </>
  );
};

export default Buscador;
