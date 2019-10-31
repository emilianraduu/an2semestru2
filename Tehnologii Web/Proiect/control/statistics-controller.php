
<?php
require 'db.php';
require './fpdf/fpdf.php';
$db = new Database;
$foundAnimals = [];
$lostAnimals = [];
$data = $db->getStatistics();
foreach ($data as $rows) {
    if ($rows["found"] != null)
        $foundAnimals = $rows["found"];
    if ($rows["lost"] != null)
        $lostAnimals = $rows["lost"];
}
if ($_POST['type'] == 'HTML') {


    echo "<h2>Found Statistics</h2>";
    echo "<table style='border: 1px solid black'>";
    echo "<th> Species </th> <th> Breed </th> <th> Found </th>";
    foreach ($foundAnimals as $foundAnimal) {
        echo "<tr >";
        foreach ($foundAnimal as $information) {
            echo "<td style='border: 1px solid black'>" . $information . "</td>";
        }
        echo "</tr>";
    }
    echo "</table>";
    echo "<h2>Lost Statistics</h2>";
    echo "<table style='border: 1px solid black'>";
    echo "<th> Species </th> <th> Breed </th> <th> Lost </th>";
    foreach ($lostAnimals as $lostAnimal) {
        echo "<tr >";
        foreach ($lostAnimal as $information) {
            echo "<td style='border: 1px solid black'>" . $information . "</td>";
        }
        echo "</tr>";
    }
    echo "</table>";
    // }
}

if ($_POST['type'] == 'CSV') {
    header('Content-Type: text/csv');
    $csv = 'Species, Breed, Found, ' . PHP_EOL;
    foreach ($foundAnimals as $foundAnimal) {
        foreach ($foundAnimal as $information) {
            $csv = $csv . $information . ', ';
        }
        $csv = $csv . PHP_EOL;
    }
    $csv = $csv . PHP_EOL;
    $csv = $csv . 'Species, Breed, Lost, ' . PHP_EOL;
    foreach ($lostAnimals as $lostAnimal) {
        foreach ($lostAnimal as $information) {
            $csv = $csv . $information . ', ';
        }
        $csv = $csv . PHP_EOL;
    }
    print_r($csv);
}



class PDF extends FPDF
{

    function BasicTable($header, $data)
    {
        foreach ($header as $col)
            $this->Cell(40, 7, $col, 1);
        $this->Ln();
        foreach ($data as $row) {
            
            foreach ($row as $value) {
                // print_r($value);
                $this->Cell(40, 6, $value, 1);
            }

            $this->Ln();
        }
    }
}

if ($_POST['type'] == 'PDF') {

    $pdf = new PDF();

    $header = array('Species', 'Breed', 'Count');


    $string = '';
    $data = [];
    foreach ($foundAnimals as $foundAnimal) {
        $data[] = $foundAnimal;
    }

    $pdf->SetFont('Arial', '', 14);
    $pdf->AddPage();
    $pdf->BasicTable($header, $data);
    foreach ($lostAnimals as $lostAnimal) {
        $data[] = $lostAnimal;
    }
    $pdf->AddPage();
    $pdf->BasicTable($header, $data);
    $pdf->Output();
}
