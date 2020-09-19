! Author: Lucas Marques Moreno
! Date: 08/2020
! Description: A program that
! can read a aifoil(.dat) file
! and aplly a rotation matrix

program airfoilPosition

    implicit none
    
    real,dimension(200,2) :: airfoil, rotateAirfoil !variable to store .dat file points (x,y) coordenates
    real :: twistAngle = 0, radAngle = 0
    real :: chord = 0 
    real :: span = 0 ! distance between the root and the plane to be placed
    real :: offset = 0, shift = 0
    CHARACTER(LEN=2) :: alignment !variable that store if the new airfoil must be align by 1/4 chord length
    CHARACTER(LEN=200) :: airfoilName
    CHARACTER(LEN=250) :: saveName
    integer :: i, col
    integer :: nlinesDat  ! number of lines in the dat file
    REAL, PARAMETER :: Pi = 3.1415927
    
    
    print *, "========================================"
    print *
    print *, "Welcome to AirFoil Postion"
    print *, "        ______         ________" 
    print *, "       /      \        |   ____|"
    print *, "      /   __   \       |  |__"
    print *, "     /   /  \   \      |  |__|"
    print *, "    /   /    \   \     |  |"
    print *, "   /   /______\   \    |  |"
    print *, "  /   __________   \   | /"
    print *, " /___/          \___\  |/"

    print *
    print *, "========================================"


!================= Input Block ==============================

    print *, "Enter the airfoil file name (with extension): "
    read(*,*) airfoilName

    print *, "How many lines does it have ?"
    read(*,*) nlinesDat

    print *, "Enter the airfoil chord: "
    read(*,*) chord

    print *, "Enter the twist angle: "
    read(*,*) twistAngle

    print *, "Enter the span distance: "
    read(*,*) span

    print *, "Do you wish to align the new foil by a 1/4 chord? (y/n)"
    read(*,*) alignment
    
    if(alignment == "n") then
        print *, "Enter the offset: "
        read(*,*) offset
    endif
    

!============================================================

!================ .Dat Manipulation Block ===================
    
    print*, "READING FILE..."

    open(10, file=airfoilName)
    
    DO i = 1,nlinesDat
        READ(10,*) (airfoil(i,col),col=1,2)
    end do
    
    close(10)
    
    if(alignment == "n") then
        shift = offset
    else
        shift = chord *0.25
    endif

    radAngle = twistAngle*Pi/180

    print*, "CALCULATING..."

    do i =1,nlinesDat
        rotateAirfoil(i,1) = (airfoil(i,1)*cos(radAngle)*chord)-shift + airfoil(i,2)*sin(radAngle)*chord
        rotateAirfoil(i,2) = (airfoil(i,2)*cos(radAngle) - airfoil(i,1)*sin(radAngle)) * chord
    end do
    
    
    saveName = "retotate_"//airfoilName

    open(20 ,file=saveName)
    
    DO i=1,nlinesDat
        !write(20,*) (rotateAirfoil(i,col),col=1,2), span
        write(20,*) rotateAirfoil(i,1), rotateAirfoil(i,1), span
    end do
    
    close(20)


    print*, "THE PROCESS COMPLETED SUCCESSFULLY"
    print*, saveName, "HAS BEEN CREATED"

!============================================================


end program airfoilPosition
