import { createContext, useContext, useState, useMemo } from "react";

export const GlobalStates = createContext();
const GlobalContext = ({ children }) => {
  const [log, setLog] = useState(false);
  const providerValue = useMemo(
    () => ({
      log,
      setLog,
    }),
    [log, setLog]
  );
  return (
    <GlobalStates.Provider value={{ providerValue }}>
      {children}
    </GlobalStates.Provider>
  );
};

export default GlobalContext;
export const useGlobalStates = () => {
  return useContext(GlobalStates);
};
